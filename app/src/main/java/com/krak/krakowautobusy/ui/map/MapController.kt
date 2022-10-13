package com.krak.krakowautobusy.ui.map

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.ui.location.UserLocation
import com.krak.krakowautobusy.ui.map.vehicledata.*
import com.krak.krakowautobusy.ui.position.ActualPositionVehicles
import com.krak.krakowautobusy.ui.position.BusStopPosition
import com.krak.krakowautobusy.ui.utility.Drawables
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import retrofit2.Response

private const val TAG = "MapController"
@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class MapController(private var map: MapView, private var context: Context) {

    private val startingPoint1 = GeoPoint(50.3107434126593, 19.61671721450658)
    private val startingpoint2 = GeoPoint(49.88512598174506, 19.545556322799532)
    private val startingPoint3 = GeoPoint(50.32107434126593, 20.379321439500526)
    private val startingPoint4 = GeoPoint(49.87252834809176, 20.461999509306546)

    private val runnableDelay: Long = 7000

    private val mapController = map.controller
    private lateinit var updateTextTask: Runnable
    val mainHandler = Handler(Looper.getMainLooper())
    private val drawables: Drawables = Drawables(context)

    init {
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        map.setMultiTouchControls(true)

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




    fun showAllTram(actualPositionTram: ActualPositionVehicles) {
        return Api.getApi().getTramPosition(actualPositionTram.lastUpdateTram, fun(response: Response<AllVehicles>)  {
            if (response.isSuccessful) {
                val allTram = response.body()!!
                actualPositionTram.lastUpdateTram = allTram.lastUpdate
                actualPositionTram.showFavouriteVehicles(map, allTram)
            }
        })
    }

    fun showAllBus(actualPositionBus: ActualPositionVehicles) {
        return Api.getApi().getBusPosition(actualPositionBus.lastUpdateBus,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null) {
                    val allBus = response.body()!!
                    actualPositionBus.lastUpdateBus = allBus.lastUpdate
                    actualPositionBus.showFavouriteVehicles(map, allBus)
                }
            }
        )
    }

    fun removeCallback() {

        mainHandler.removeCallbacks(updateTextTask)
    }


    fun addLocationMarkerToMap(userLocation: UserLocation) {
        val zoomLevelWhenAddLocationIcon=15
        mapController.setZoom(zoomLevelWhenAddLocationIcon)
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
    }

    fun removeShowingBusStops(busStopPosition: BusStopPosition) {
        busStopPosition.hiddenAllBusStops(map)
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

    fun startShowVehicle(actualPositionVehicle: ActualPositionVehicles) {
        updateTextTask = object : Runnable {
            override fun run() {
                showAllBus(actualPositionVehicle)
                showAllTram(actualPositionVehicle)
                mainHandler.postDelayed(this, runnableDelay)
            }
        }
        mainHandler.post(updateTextTask)

    }

    fun loadMarkerIntoMap(actualPositionVehicle: ActualPositionVehicles) {
        actualPositionVehicle.addPolylineIntoMap(map)
        actualPositionVehicle.lodaIconIntoMap()
    }

}