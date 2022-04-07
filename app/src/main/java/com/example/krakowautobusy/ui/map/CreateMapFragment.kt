package com.example.krakowautobusy.ui.map

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.MapActivityBinding
import com.example.krakowautobusy.ui.map.vehicledata.ActualPositionVehicles
import com.example.krakowautobusy.ui.map.vehicledata.BusStopPosition
import com.example.krakowautobusy.ui.map.vehicledata.Utilities
import kotlinx.coroutines.*
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
import org.osmdroid.views.overlay.Polyline

import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class CreateMapFragment : Fragment() {

    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    private lateinit var busStopIconDrawable: Drawable
    private lateinit var tramIconDrawable: Drawable
    private lateinit var busIconDrawable: Drawable
    private lateinit var busIconTrackingDrawable: Drawable
    private lateinit var tramIconTracingDrawable: Drawable
    private lateinit var resizedBusStopIcon: Drawable
    private lateinit var resizedTramIcon: Drawable
    private lateinit var resizedBusIcon: Drawable

    private lateinit var updateTextTask: Runnable
    private lateinit var busStopPosition: BusStopPosition
    private lateinit var userLocation: UserLocation
    val utilites = Utilities()
    val mainHandler = Handler(Looper.getMainLooper())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView

        userLocation = UserLocation(requireActivity() as AppCompatActivity)
        busStopPosition = BusStopPosition(requireContext())

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
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_icon_tram)!!
        tramIconDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_icon_bus)!!
        tramIconTracingDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_icon_tram_tracking)!!
        busIconTrackingDrawable =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_icon_bus_tracking)!!

        busIconDrawable = utilites.resizeDrawable(65,65,busIconDrawable,requireContext())
        tramIconDrawable = utilites.resizeDrawable(65,65,tramIconDrawable,requireContext())
        busIconTrackingDrawable = utilites.resizeDrawable(65,65,busIconTrackingDrawable,requireContext())
        tramIconTracingDrawable = utilites.resizeDrawable(65,65,tramIconTracingDrawable,requireContext())

        mapController.setCenter(startingPoint)

//        busStopPosition.showAllBusStops(
//            utilites.resizeDrawable(
//                25, 25, busStopIconDrawable,
//                requireContext()
//            ), map
//        )
        resizeIcons()
        val trackedRoute = Polyline()
        map.overlays.add(trackedRoute)
        val actualPositionVehicles = ActualPositionVehicles(busIconDrawable,
            tramIconDrawable, busIconTrackingDrawable, tramIconTracingDrawable)
        actualPositionVehicles.createPolyline(trackedRoute)
        updateTextTask = object : Runnable {
            override fun run() {
                actualPositionVehicles.showAllVehicle(
                    map
                )
                mainHandler.postDelayed(this, 7000)
            }
        }
        mainHandler.post(updateTextTask)
        Log.i("AAA", "OnCreateCalled")

        userLocation.getLastLocation()

        return binding.root
    }

    private fun resizeIcons() {
        var currentZoomLevel = map.zoomLevel
        map.setMapListener(
            object : MapListener {
                override fun onZoom(e: ZoomEvent?): Boolean {
                    if (currentZoomLevel != map.zoomLevel) {
                        userLocation.getLastLocation()
                        resizedBusStopIcon =
                            utilites.resizeDrawable(
                                utilites.setIconSize(map.zoomLevel),
                                utilites.setIconSize(map.zoomLevel),
                                busStopIconDrawable,
                                requireContext()
                            )
                        resizedBusIcon =
                            utilites.resizeDrawable(
                                utilites.setIconSize(map.zoomLevel) * 3,
                                utilites.setIconSize(map.zoomLevel) * 3,
                                busIconDrawable,
                                requireContext()
                            )
                        resizedTramIcon =
                            utilites.resizeDrawable(
                                utilites.setIconSize(map.zoomLevel) * 3,
                                utilites.setIconSize(map.zoomLevel) * 3,
                                tramIconDrawable,
                                requireContext()
                            )
                        for ((index) in map.overlays.withIndex()) {
                            if (map.overlays[index] is Marker) {
                                val marker = map.overlays[index] as Marker

                                when ((map.overlays[index] as Marker).id) {
                                    "busStop" -> {
                                        marker.icon = resizedBusStopIcon
                                        map.overlays[index] = marker
                                    }
                                    "bus" -> {
                                        marker.icon = resizedBusIcon
                                        map.overlays[index] = marker
                                    }
                                    "tram" -> {
                                        marker.icon = resizedTramIcon
                                        map.overlays[index] = marker
                                    }
                                }
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

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainHandler.removeCallbacks(updateTextTask)
        Log.i("AAA", "OnDestroyVewCalled")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("AAA", "OnDestroyCalled")

    }
}