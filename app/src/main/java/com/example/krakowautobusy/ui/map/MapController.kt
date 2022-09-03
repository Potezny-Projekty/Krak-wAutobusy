package com.example.krakowautobusy.ui.map

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.ui.map.vehicledata.*
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import retrofit2.Response

private const val TAG = "MapController"
@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class MapController(private var map: MapView, private var context: Context) {

    private val STARTING_POINT1 = GeoPoint(50.3107434126593, 19.61671721450658)
    private val STARTING_POINT2 = GeoPoint(49.88512598174506, 19.545556322799532)
    private val STARTING_POINT3 = GeoPoint(50.32107434126593, 20.379321439500526)
    private val STARTING_POINT4 = GeoPoint(49.87252834809176, 20.461999509306546)

    private val RUNNABLE_DELAY: Long = 7000

    private val mapController = map.controller
    private lateinit var updateTextTask: Runnable
    val mainHandler = Handler(Looper.getMainLooper())
    private val drawables: Drawables = Drawables(context)

    init {
        map.setTileSource(TileSourceFactory.MAPNIK)

        // hiding +- buttons used to change map zoom
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        // unlocking zoom
        map.setMultiTouchControls(true)

        // setting map scope
        createMapScope(map)
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

    fun createLocationMarker(userLocation: UserLocation, drawables: Drawables) {
        userLocation.createLocationMarker(map, drawables.userLocationIconDrawable)
    }

    fun drawAllVehicles(actualPositionVehicles: ActualPositionVehicles) {
        //actualPositionVehicles.lodaIconIntoMap()
        //actualPositionVehicles.getActualPosition(map)
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

    fun startShowingVehiclesOnTheMap(isFavourite : LiveData<Boolean>,
                                     viewLifecycleOwner : LifecycleOwner,
                                     actualPositionVehicle: ActualPositionVehicles) {
        actualPositionVehicle.addPolylineIntoMap(map)
        val actualPositionFavouriteVehicle =
            ActualPositionFavouriteVehicle(drawables)
        actualPositionFavouriteVehicle.addPolylineIntoMap(map)
        var actualPositionAllOrFavouriteVehicle = actualPositionVehicle
        actualPositionAllOrFavouriteVehicle.lodaIconIntoMap()
        isFavourite.observe(viewLifecycleOwner, Observer {
            Log.i("MAPPPA", it.toString())
            actualPositionAllOrFavouriteVehicle.hiddenMarkers(map)
            actualPositionAllOrFavouriteVehicle.removeTrackedVehicle()
            actualPositionAllOrFavouriteVehicle = if (it) {
                actualPositionFavouriteVehicle
            } else {
                actualPositionVehicle
            }
            showAllBus(actualPositionAllOrFavouriteVehicle)
            showAllTram(actualPositionAllOrFavouriteVehicle)
            actualPositionAllOrFavouriteVehicle.showMarkers(map)
        })

        updateTextTask = object : Runnable {
            override fun run() {
                showAllBus(actualPositionAllOrFavouriteVehicle)
                showAllTram(actualPositionAllOrFavouriteVehicle)
                Log.i("POZYCJA", actualPositionAllOrFavouriteVehicle.toString())
                mainHandler.postDelayed(this, RUNNABLE_DELAY)
            }
        }
        mainHandler.post(updateTextTask)
    }


    private fun showAllTram(actualPositionTram: ActualPositionVehicles) {
        return Api.getApi().getTramPosition(actualPositionTram.lastUpdateTram, fun(response: Response<AllVehicles>)  {
            if (response.isSuccessful) {
                val allTram = response.body()!!
                actualPositionTram.lastUpdateTram = allTram.lastUpdate
                actualPositionTram.showAllVehicle(map, allTram)
            }
        })
    }

    private fun showAllBus(actualPositionBus: ActualPositionVehicles) {
        return Api.getApi().getBusPosition(actualPositionBus.lastUpdateBus,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null) {
                    val allBus = response.body()!!
                    actualPositionBus.lastUpdateBus = allBus.lastUpdate
                    actualPositionBus.showAllVehicle(map, allBus)
                }
            }
        )
    }

    fun removeCallback() {
        mainHandler.removeCallbacks(updateTextTask)
    }

    fun luchCallback() {
        mainHandler.post(updateTextTask)
    }

    fun addLocationMarkerToMap(userLocation: UserLocation) {
        map.overlays.add(userLocation.locationMarker)
    }

    fun removeLocationMarkerFromMap(userLocation: UserLocation) {
        map.overlays.remove(userLocation.locationMarker)
    }

    fun loadingIcon(actualPositionVehicles: ActualPositionVehicles) {
        actualPositionVehicles.lodaIconIntoMap()
    }

    fun removeShowingAllVehicles(actualPositionVehicles: ActualPositionVehicles) {
        actualPositionVehicles.hiddenMarkers(map)
        removeCallback()

    }

    fun removeShowingBusStops(busStopPosition: BusStopPosition) {
        busStopPosition.hiddenAllBusStops(map)
        luchCallback()
    }

    fun showAllBusStops(busStopPosition: BusStopPosition) {
        busStopPosition.showAllBusStops(map)
    }


    fun createAllBusStopsMarker(busStopPosition: BusStopPosition) {
        busStopPosition.createAllBusStopsMarkers(map)
    }

    fun showAllVehicleMarker(actualPositionVehicles: ActualPositionVehicles) {
        actualPositionVehicles.showMarkers(map)
    }

    fun removeTrackedVehicle(actualPositionVehicles: ActualPositionVehicles) {
        actualPositionVehicles.removeTrackedVehicle()
    }

    fun setOSMRoadManager(actualPositionVehicles: ActualPositionVehicles) {
        actualPositionVehicles.setRoadManager(map)
    }

}