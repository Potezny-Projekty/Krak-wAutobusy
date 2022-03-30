package com.example.krakowautobusy.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
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
    private lateinit var iconFromDrawable: Drawable
    private lateinit var resizedDrawable: Drawable
    private lateinit var busStopData: ArrayList<Select_db_BusStopInterface.BusStopRow>
    private var busStopPosition: ArrayList<GeoPoint> = ArrayList()

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

        iconFromDrawable = AppCompatResources.getDrawable(requireContext(), R.drawable.bus_icon)!!

        mapController.setCenter(startingPoint)
        showAllBusStops(resizeDrawable(10, 10))
//        showBusStop(resizeDrawable(10, 10),50.06173293019267,19.937894523426294)
        resizeIcons()

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

    private fun resizeDrawable(x: Int, y: Int): Drawable {
        val bitmap: Bitmap = getBitmapFromVectorDrawable(requireContext(), R.drawable.bus_icon)!!
        val resized = Bitmap.createScaledBitmap(bitmap, x, y, true)
        return BitmapDrawable(resources, resized)
    }

    private fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun resizeIcons() {
        var currentZoomLevel = map.zoomLevel
        map.setMapListener(
            object : MapListener {
                override fun onZoom(e: ZoomEvent?): Boolean {
                    if (currentZoomLevel != map.zoomLevel) {
                        resizedDrawable =
                            resizeDrawable(setIconSize(map.zoomLevel), setIconSize(map.zoomLevel))
                        map.overlays.clear()
                        showAllBusStops(resizedDrawable)
                        Log.i("MAP", "Current zoom level " + map.zoomLevel.toString())
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
}