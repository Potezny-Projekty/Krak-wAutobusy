package com.example.krakowautobusy.ui.map.vehicledata

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.VehicleStopData
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.utils.BonusPackHelper
import org.osmdroid.views.MapView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class BusStopPosition(private val busStopIconDrawable : Drawable) {

    private var busStopMarkers: BusStopMarkerClusterer? = null


    fun createAllBusStopsMarkers(map : MapView) {
        val busStopMarkerCollectionRadiusForClustering = 170
        Executors.newSingleThreadScheduledExecutor().schedule({
            val busStopData = Api.getApi().getAllVehiclesStop()
            busStopMarkers = BusStopMarkerClusterer(map.context)
            busStopData.forEach {
                busStopMarkers!!.add(createBusStopMarker(map, it))
            }
           busStopMarkers!!.setRadius(busStopMarkerCollectionRadiusForClustering)
        },0, TimeUnit.SECONDS)
    }

    private fun createBusStopMarker(map : MapView,
                                    vehicleStopData: VehicleStopData) : BusStopMarker {

         val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)
        marker.icon = busStopIconDrawable
        marker.title = vehicleStopData.name

        return marker
    }

    fun showAllBusStops(map : MapView) {
        map.overlays.add(busStopMarkers)
    }

    fun hiddenAllBusStops(map : MapView) {
        map.overlays.remove(busStopMarkers)

    }

}