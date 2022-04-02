package com.example.krakowautobusy.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.R
import com.example.krakowautobusy.database.Database
import com.example.krakowautobusy.database.Select_db_BusStop
import com.example.krakowautobusy.database.Select_db_BusStopInterface
import com.example.krakowautobusy.databinding.MapActivityBinding
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


@Suppress("DEPRECATION")
class CreateMapFragment : Fragment() {

    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    private lateinit var busStopIconDrawable: Drawable
    private lateinit var tramIconDrawable: Drawable
    private lateinit var busIconDrawable: Drawable
    private lateinit var resizedBusStopIcon: Drawable
    private lateinit var resizedTramIcon: Drawable
    private lateinit var resizedBusIcon: Drawable

    private lateinit var busStopData: ArrayList<Select_db_BusStopInterface.BusStopRow>
    private var busStopPosition: ArrayList<GeoPoint> = ArrayList()
    private lateinit var updateTextTask: Runnable
    val actualPositionVehicles = ActualPositionVehicles()
    val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView

        busStopData = getBusStopData()
        calculateBusStopPos()

        map.setTileSource(TileSourceFactory.MAPNIK)
        val mapController = map.controller

        // ukrycie przycisków + - zoomujących mapę
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        // odblokowanie zoomowania
        map.setMultiTouchControls(true)

        // ograniczenie zakresu do którego można przesunąć mapę
        val startingPoint2 = GeoPoint(50.3107434126593, 19.61671721450658)
        val startingPoint3 = GeoPoint(49.88512598174506, 19.545556322799532)
        val startingPoint4 = GeoPoint(50.32107434126593, 20.379321439500526)
        val startingPoint5 = GeoPoint(49.87252834809176, 20.461999509306546)

        val arrayList: ArrayList<GeoPoint> = ArrayList()
        arrayList.add(startingPoint2)
        arrayList.add(startingPoint3)
        arrayList.add(startingPoint4)
        arrayList.add(startingPoint5)

        val boundingBox = BoundingBox.fromGeoPoints(arrayList)

        map.setScrollableAreaLimitDouble(boundingBox)

        map.minZoomLevel = 13.0
        map.maxZoomLevel = 20.0

        mapController.setZoom(14.0)

        val startingPoint = GeoPoint(50.06173293019267, 19.937894523426294)

        busStopIconDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.bus_icon)!!
        busIconDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_icon_bus)!!
        tramIconDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_icon_tram)!!

        mapController.setCenter(startingPoint)
        showAllBusStops(resizeDrawable(10, 10, busStopIconDrawable))
        //showBusStop(resizeDrawable(10, 10), 50.06173293019267, 19.937894523426294)
        resizeIcons()

        val mainHandler = Handler(Looper.getMainLooper())

        updateTextTask = object : Runnable {
            override fun run() {
                actualPositionVehicles.showAllVehicle(map, context, busIconDrawable,tramIconDrawable)
                mainHandler.postDelayed(this, 3000)
            }
        }
        mainHandler.post(updateTextTask)

        return binding.root
    }

    fun showAllBusStops(icon: Drawable) {
        for ((index, elem) in busStopData.withIndex()) {
            val startingPoint = busStopPosition[index]

            val marker = Marker(map)
            marker.position = startingPoint
            marker.icon = icon
            marker.title = elem.nameBusStop + " " + elem.id
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            marker.id = "busStop"
            map.overlays.add(marker)
        }
        map.invalidate()
    }

    fun calculateBusStopPos(): ArrayList<GeoPoint> {
        for (elem in busStopData) {
            busStopPosition.add(
                GeoPoint(
                    (elem.latitude / 3600000f).toDouble(),
                    (elem.longitude / 3600000f).toDouble()
                )
            )
        }
        return busStopPosition
    }

    fun showBusStop(icon: Drawable, latitude: Double, longtitude: Double) {
        val startingPoint = GeoPoint(latitude, longtitude)

        val marker = Marker(map)
        marker.position = startingPoint
        marker.icon = icon
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)

        map.invalidate()
    }

    fun getBusStopData(): ArrayList<Select_db_BusStopInterface.BusStopRow> {
        val connection = Select_db_BusStop()
        val instance = Database.getInstance(requireActivity())
        return connection.selectBusStopAll(instance.readableDatabase)
    }

    private fun resizeDrawable(x: Int, y: Int, icon: Drawable): Drawable {
        val bitmap: Bitmap = getBitmapFromVectorDrawable(requireContext(), icon)!!
        val resized = Bitmap.createScaledBitmap(bitmap, x, y, true)
        return BitmapDrawable(resources, resized)
    }

    private fun getBitmapFromVectorDrawable(context: Context, icon: Drawable): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            icon!!.intrinsicWidth,
            icon.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        icon.setBounds(0, 0, canvas.width, canvas.height)
        icon.draw(canvas)
        return bitmap
    }

    private fun resizeIcons() {
        var currentZoomLevel = map.zoomLevel
        map.setMapListener(
            object : MapListener {
                override fun onZoom(e: ZoomEvent?): Boolean {
                    if (currentZoomLevel != map.zoomLevel) {
                        resizedBusStopIcon =
                            resizeDrawable(
                                setIconSize(map.zoomLevel),
                                setIconSize(map.zoomLevel),
                                busStopIconDrawable
                            )
                        resizedBusIcon =
                            resizeDrawable(
                                setIconSize(map.zoomLevel),
                                setIconSize(map.zoomLevel),
                                busIconDrawable
                            )
                        resizedTramIcon =
                            resizeDrawable(
                                setIconSize(map.zoomLevel)*3,
                                setIconSize(map.zoomLevel)*3,
                                tramIconDrawable
                            )
                        for ((index) in map.overlays.withIndex()) {
                            if((map.overlays.get(index) as Marker).id == "busStop"){
                                val marker = map.overlays.get(index) as Marker
                                marker.icon = resizedBusStopIcon
                                map.overlays[index] = marker
                            }
                            if((map.overlays.get(index) as Marker).id == "bus"){
                                val marker = map.overlays.get(index) as Marker
                                marker.icon = resizedBusIcon
                                map.overlays[index] = marker
                            }
                            if((map.overlays.get(index) as Marker).id == "tram"){
                                val marker = map.overlays.get(index) as Marker
                                marker.icon = resizedTramIcon
                                map.overlays[index] = marker
                            }

                        }
                        map.invalidate()
                        currentZoomLevel = map.zoomLevel
                    }
                    return true
                }

                override fun onScroll(e: ScrollEvent?): Boolean {
                    return true
                }
            },
        )
    }

    private fun setIconSize(zoomLevel: Int): Int {
        var iconSize = 15
        when (zoomLevel) {
            13 -> {
                iconSize = 15
            }
            14 -> {
                iconSize = 18
            }
            15 -> {
                iconSize = 25
            }
            16 -> {
                iconSize = 30
            }
            17 -> {
                iconSize = 35
            }
            18 -> {
                iconSize = 40
            }
            19 -> {
                iconSize = 45
            }
            20 -> {
                iconSize = 50
            }
        }
        return iconSize
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("AAA", "OnDestroyCalled")
        mainHandler.removeCallbacks(updateTextTask)
    }
}