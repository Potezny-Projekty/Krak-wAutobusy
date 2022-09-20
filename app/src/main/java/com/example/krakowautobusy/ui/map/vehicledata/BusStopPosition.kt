package com.example.krakowautobusy.ui.map.vehicledata

import android.graphics.drawable.Drawable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.VehicleStopData
import com.example.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


open class BusStopPosition(private val busStopIconDrawable : Drawable) {

    private var busStopMarkers: RadiusMarkerClusterer? = null


    open fun createAllBusStopsMarkers(map : MapView) {
        val busStopMarkerCollectionRadiusForClustering = 200
        val busStopData = Api.getApi().getAllVehiclesStop()
        busStopMarkers = BusStopMarkerCluster(map.context)
        busStopData.forEach {
            busStopMarkers!!.add(createBusStopMarker(map, it))
        }
       busStopMarkers!!.setRadius(busStopMarkerCollectionRadiusForClustering)
    }

    protected fun createBusStopMarker(map : MapView,
                                    vehicleStopData: VehicleStopData) : BusStopMarker {

         val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)

        marker.infoWindow.view.setOnClickListener({
/*
            val bundle = bundleOf(
                Bundle_Vehicle_Stop.ID_VEHICLE_STOP.nameBundle to
                        selectedItem.idVehicleStop.toString(),
                Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                        selectedItem.name,
                Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                        selectedItem.idStopPoint.toString()

            )
            //bundle
            Navigation.findNavController(view).navigate(R.id.action_navigate_to_details_vehiclestop,bundle);

            */

        })

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