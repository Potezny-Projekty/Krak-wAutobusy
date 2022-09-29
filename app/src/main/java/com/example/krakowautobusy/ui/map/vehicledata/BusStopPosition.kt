package com.example.krakowautobusy.ui.map.vehicledata

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.krakowautobusy.MainActivity
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.VehicleStopData
import com.example.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Marker
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


open class BusStopPosition(private val busStopIconDrawable : Drawable) {

    private var busStopMarkers: RadiusMarkerClusterer? = null
    private var trackedBusStops : Marker? = null


    open fun createAllBusStopsMarkers(map : MapView) {
        val busStopMarkerCollectionRadiusForClustering = 200
        val busStopData = Api.getApi().getAllVehiclesStop()
        busStopMarkers = BusStopMarkerCluster(map.context)
        busStopData.forEach {
            busStopMarkers!!.add(createBusStopMarker(map, it))
        }
       busStopMarkers!!.setRadius(busStopMarkerCollectionRadiusForClustering)
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    protected fun createBusStopMarker(map : MapView,
                                      vehicleStopData: VehicleStopData) : BusStopMarker {
        Log.e("blad","blad2")
         val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)

        marker.setOnMarkerClickListener { marker, mapView ->
            trackedBusStops = marker
            marker.showInfoWindow()
            true
        }

        marker.infoWindow.view.background=AppCompatResources.getDrawable(map.context,R.drawable.shadow)
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
            Navigation.findNavController(navView)
                .navigate(R.id.actionnavigatedetailesstop, bundle);


            true})*/













        })


        marker.infoWindow.view.setOnTouchListener { x, event ->

            if(event.action==MotionEvent.ACTION_DOWN) {


                Log.e("blad", "blad")
                val bundle = bundleOf(
                    Bundle_Vehicle_Stop.ID_VEHICLE_STOP.nameBundle to
                            marker.busStop.idVehicleStop,
                    Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                            marker.busStop.name,
                    Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                            marker.busStop.idStopPoint

                )
                //bundle


                Navigation.findNavController(x)
                    .navigate(R.id.actionnavigatedetailesstop, bundle);
            }

            true
        }

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
        trackedBusStops?.closeInfoWindow()
        map.invalidate()
    }

}