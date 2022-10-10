package com.krak.krakowautobusy.ui.map.vehicledata

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.google.android.material.textview.MaterialTextView
import com.krak.krakowautobusy.BusStopInfoWindow
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.VehicleStopData
import com.krak.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow
import org.w3c.dom.Text


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

     //  val markerToast = BusStopInfoWindow(map, vehicleStopData.name)

           // val markerToast=


         val marker = BusStopMarker(map, vehicleStopData)
        marker.position = ConvertUnits.convertToGeoPoint(vehicleStopData.latitude,
            vehicleStopData.longitude)

        marker.setOnMarkerClickListener { marker, mapView ->
            trackedBusStops = marker
            marker.showInfoWindow()
            true
        }


      //  marker.infoWindow = markerToast
        marker.infoWindow.view.background= AppCompatResources.getDrawable(map.context,R.drawable.snippetgroupvehiclestop)


        marker.infoWindow.view.setOnTouchListener { x, event ->


        //  var name=""
            var count =x as ViewGroup
            var name=(((x.getChildAt(1) as LinearLayout).getChildAt(0) as LinearLayout) .getChildAt(0) as TextView).text.toString()
           /* Log.e("jajo","wyniczek:"+namexx)
                for (i in 0..count.childCount){
                var ve=x.getChildAt(i)
                if(ve is TextView){
                //    Log.e("jajo","mamcie"+i+"  "+(ve as TextView).text)

                }
                if(ve is LinearLayout){
                    Log.e("jajo","level1[ "+i)
                    var cc=ve.childCount
                    for (i in 0..cc) {
                        var va=ve.getChildAt(i)
                        if(va is TextView){
                          //  Log.e("jajo","mamcie va"+i+"  "+(va as TextView).text)

                        }
                        if(va!=null){
                         //   Log.e("jajo","2 "+ve.javaClass.name)
                        }

                        if(va is LinearLayout){
                            Log.e("jajo","level2[ "+i)
                            var qw=va.childCount
                            for (i in 0..qw) {
                                var qq=va.getChildAt(i)
                                if(qq is TextView){
                                //    Log.e("jajo","mamcie va9"+i+"  "+(qq as TextView).text)
                                    if((qq as TextView).text.toString()!=""){
                                        name= (qq as TextView).text as String

                                        Log.e("jajo","level3[ "+i)
                                    }




                                  //  Log.e("jajo","wyniczek"+name)


                                }

                                if(qq is com.google.android.material.textview.MaterialTextView){
                               //     Log.e("jajo","mamcie va9"+i+"  "+(qq as MaterialTextView).text)
                                }
                                if(qq!=null){
                              //      Log.e("jajo",qq.javaClass.name)
                                }}
                        }

                    }
                    }

                if(ve!=null){
                    Log.e("jajo",ve.javaClass.name)
                }


                }
*/





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
                            "",
                    Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                            name,
                    Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                           ""

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