package com.example.krakowautobusy.ui.map.vehicledata

import android.graphics.drawable.Drawable
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.VehicleStopData
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


open class BusStopPosition(private val busStopIconDrawable : Drawable) {

    private var busStopMarkers: RadiusMarkerClusterer? = null


    open fun createAllBusStopsMarkers(map : MapView) {
        val busStopMarkerCollectionRadiusForClustering = 200
        Executors.newSingleThreadScheduledExecutor().schedule({
            val busStopData = Api.getApi().getAllVehiclesStop()
            busStopMarkers = BusStopMarkerCluster(map.context)
            busStopData.forEach {
                busStopMarkers!!.add(createBusStopMarker(map, it))
            }
           busStopMarkers!!.setRadius(busStopMarkerCollectionRadiusForClustering)
        },0, TimeUnit.SECONDS)
    }

    protected fun createBusStopMarker(map : MapView,
                                    vehicleStopData: VehicleStopData) : BusStopMarker {

         val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)
        marker.icon = busStopIconDrawable
        marker.title = vehicleStopData.name

        return marker
    }

    open fun showAllBusStops(map : MapView) {
        map.overlays.add(busStopMarkers)
        map.invalidate()
    }

    open fun hiddenAllBusStops(map : MapView) {
        map.overlays.remove(busStopMarkers)
        map.invalidate()
    }

}