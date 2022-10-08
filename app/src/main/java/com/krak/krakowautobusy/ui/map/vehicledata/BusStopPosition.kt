package com.krak.krakowautobusy.ui.map.vehicledata

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.VehicleStopData
import com.krak.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


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

        marker.infoWindow.view.background=AppCompatResources.getDrawable(map.context,R.drawable.snippetgroupvehiclestop)



        marker.infoWindow.view.setOnTouchListener { x, event ->
           // var x=x.findViewById<TextView>(R.id.title);

          // Log.e("danee","kUrwa"+x.text)

            if(event.action==MotionEvent.ACTION_DOWN) {
                Log.e("danee", "idV " + marker.busStop.idVehicleStop)
                Log.e("danee", "Nam " + vehicleStopData.name)
                Log.e("danee", "idS " + marker.busStop.idStopPoint)
                Log.e("danee", "idS " + marker.title)

                Log.e("blad", "blad88")
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