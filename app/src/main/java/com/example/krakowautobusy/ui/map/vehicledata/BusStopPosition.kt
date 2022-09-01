package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.Database
import com.example.krakowautobusy.database.VehicleStop
import com.example.krakowautobusy.database.VehicleStopData
import org.osmdroid.views.MapView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class BusStopPosition(private val busStopIconDrawable : Drawable) {

    private val busStopMarkers: ArrayList<BusStopMarker> = ArrayList()


    fun createAllBusStopsMarkers(map : MapView) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val busStopData = Api.getApi().getAllVehiclesStop()
            busStopData.forEach {
                busStopMarkers.add(createBusStopMarker(map, it))
            }
        },0, TimeUnit.SECONDS)
    }

    private fun createBusStopMarker(map : MapView,
                                    vehicleStopData: VehicleStopData) : BusStopMarker {
         val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)
        marker.icon = busStopIconDrawable
        marker.snippet = vehicleStopData.name

        return marker
    }

    fun showAllBusStops(map : MapView) {
        map.overlays.addAll(busStopMarkers)
    }

    fun hiddenAllBusStops(map : MapView) {
        map.overlays.removeAll(busStopMarkers)
        busStopMarkers[0].closeInfoWindow()
    }

}