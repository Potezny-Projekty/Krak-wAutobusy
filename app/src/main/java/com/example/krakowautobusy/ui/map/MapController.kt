package com.example.krakowautobusy.ui.map

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import com.example.krakowautobusy.R
import com.example.krakowautobusy.ui.map.vehicledata.ActualPositionVehicles
import com.example.krakowautobusy.ui.map.vehicledata.BusStopPosition
import com.example.krakowautobusy.ui.map.vehicledata.UserLocation
import com.example.krakowautobusy.ui.map.vehicledata.Utilities
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

private const val TAG = "MapController"
@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class MapController(private var map: MapView, private var context: Context) {

    private val STARTING_POINT1 = GeoPoint(50.3107434126593, 19.61671721450658)
    private val STARTING_POINT2 = GeoPoint(49.88512598174506, 19.545556322799532)
    private val STARTING_POINT3 = GeoPoint(50.32107434126593, 20.379321439500526)
    private val STARTING_POINT4 = GeoPoint(49.87252834809176, 20.461999509306546)

    private val mapController = map.controller
    private val trackedRoute = Polyline()
    private val routeWidth = 10.0f
    private val routeColor = "#39DD00"

    init {
        map.setTileSource(TileSourceFactory.MAPNIK)

        // hiding +- buttons used to change map zoom
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        // unlocking zoom
        map.setMultiTouchControls(true)

        // setting map scope
        //createMapScope(map)
    }

    fun setZoomLevels(minZoomLevel: Double, maxZoomLevel: Double, startingZoom: Double) {
        map.minZoomLevel = minZoomLevel
        map.maxZoomLevel = maxZoomLevel
        mapController.setZoom(startingZoom)
    }

    fun setStartingPoint(x: Double, y: Double) {
        val startingPoint = GeoPoint(x, y)
        mapController.setCenter(startingPoint)
    }

    fun drawLocationMarker(userLocation: UserLocation, drawables: Drawables) {
        userLocation.drawLocationMarker(map, drawables.userLocationIconDrawable)
    }

    fun drawTrackedRoute(actualPositionVehicles: ActualPositionVehicles) {
        map.overlays.add(trackedRoute)
        actualPositionVehicles.createPolyline(trackedRoute, routeWidth, routeColor)
    }

    fun drawAllVehicles(actualPositionVehicles: ActualPositionVehicles) {
        actualPositionVehicles.showAllVehicle(map)
    }

    fun drawAllBusStops(drawables: Drawables) {
        var busStopPosition = BusStopPosition(context)
        busStopPosition.showAllBusStops(map, drawables)
    }

    private fun createMapScope(map: MapView) {
        val arrayList: ArrayList<GeoPoint> = ArrayList()
        arrayList.add(STARTING_POINT1)
        arrayList.add(STARTING_POINT2)
        arrayList.add(STARTING_POINT3)
        arrayList.add(STARTING_POINT4)

        val boundingBox = BoundingBox.fromGeoPoints(arrayList)

        map.setScrollableAreaLimitDouble(boundingBox)
    }

    fun onZoomChangeListener(drawables: Drawables, utilites: Utilities) {
        var currentZoomLevel = map.zoomLevel
        map.setMapListener(
            object : MapListener {
                override fun onZoom(e: ZoomEvent?): Boolean {
                    Log.i(TAG, map.zoomLevel.toString())
                    if (currentZoomLevel != map.zoomLevel) {
                        drawables.resizeIcons(drawables, utilites, map.zoomLevel)
                        for ((index) in map.overlays.withIndex()) {
                            if (map.overlays[index] is Marker) {
                                val marker = map.overlays[index] as Marker
                                when ((map.overlays[index] as Marker).id) {
                                    "busStop" -> {
                                        marker.icon = drawables.resizedBusStopIcon
                                    }
                                    "bus" -> {
                                        marker.icon = drawables.resizedBusIcon
                                    }
                                    "tram" -> {
                                        marker.icon = drawables.resizedTramIcon
                                    }
                                    "location" -> {
                                        marker.icon = drawables.resizedUserLocationIcon
                                    }
                                    "busFocused" -> {
                                        marker.icon = drawables.resizedBusIconTracking
                                    }
                                    "tramFocused" -> {
                                        marker.icon = drawables.resizedTramIconTracking
                                    }
                                }
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
}