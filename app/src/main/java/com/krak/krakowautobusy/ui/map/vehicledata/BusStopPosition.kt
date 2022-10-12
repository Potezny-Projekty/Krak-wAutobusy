package com.krak.krakowautobusy.ui.map.vehicledata
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
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




    @SuppressLint("ClickableViewAccessibility")
    private fun setMarkerInfoWindowClickToNavigateVehicleDetails(marker:BusStopMarker){
        val defaultValue=""

        marker.infoWindow.view.setOnTouchListener { x, event ->

            var count =x as ViewGroup
            var name=(((x.getChildAt(1) as LinearLayout).getChildAt(0) as LinearLayout) .getChildAt(0) as TextView).text.toString()


            if(event.action==MotionEvent.ACTION_DOWN) {

                val bundle = bundleOf(
                    Bundle_Vehicle_Stop.ID_VEHICLE_STOP.nameBundle to
                            defaultValue,
                    Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                            name,
                    Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                            defaultValue

                )


                Navigation.findNavController(x)
                    .navigate(R.id.actionnavigatedetailesstop, bundle);
            }

            true
        }
    }

    private fun setMarkerClickShowSnippet(marker:BusStopMarker){
        marker.setOnMarkerClickListener { marker, _ ->
            trackedBusStops = marker
            marker.showInfoWindow()
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    protected fun createBusStopMarker(map : MapView,
                                      vehicleStopData: VehicleStopData) : BusStopMarker {


        val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)


        setMarkerClickShowSnippet(marker)

        marker.infoWindow.view.background= AppCompatResources.getDrawable(map.context,R.drawable.snippetgroupvehiclestop)
        setMarkerInfoWindowClickToNavigateVehicleDetails(marker)

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