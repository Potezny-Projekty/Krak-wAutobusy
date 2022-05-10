package com.example.krakowautobusy.ui.map.vehicledata

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.ui.map.Drawables
import com.example.krakowautobusy.ui.map.network.ActualPositionApi
import com.example.krakowautobusy.ui.map.network.RetrofitHelperBus
import com.example.krakowautobusy.ui.map.network.RetrofitHelperTram
import com.google.gson.JsonObject
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ActualPositionVehicles(var drawables: Drawables) {
    private var lastUpdateBus: Long = 0
    private var lastUpdateTram: Long = 0
    private var markers = mutableMapOf<String, Marker>()
    private var trackedRoute = Polyline()
    private var traveledRoute = Polyline()
    private val busHelperInstance = RetrofitHelperBus
        .getInstance().create(ActualPositionApi::class.java)
    private val tramHelperInstance = RetrofitHelperTram.getInstance()
        .create(ActualPositionApi::class.java)
    private val fullAngle = 360F
    private var enabled = true
    private var trackingVehicle: Marker? = null
    private val typeVehicleBus = "bus"
    private val typeVehicleBusFocused = "busFocused"
    private val typeVehicleTram = "tram"
    val NO_ELEMENT=0


    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    private fun showAllVehicle(map: MapView, allVehicles: AllVehicles) {
        val listOfAllVehicle = allVehicles.vehicles
        listOfAllVehicle
            .filter { !it.isDeleted }
            .forEach {
                if (markers.containsKey(it.id)) {
                    val drawVehicleMarker = markers[it.id]!!
                    updateMarkerPosition(drawVehicleMarker, it, map)
                } else {
                    drawMarkerVehiclesOnMap(it, map)
            }
        }
    }

    private fun updateMarkerPosition(marker : Marker, vehicle: Vehicle, map : MapView) {
        if (vehicle.path.size > NO_ELEMENT) {
            val lastPosition = ConvertUnits.convertToGeoPoint(vehicle.path[vehicle.path.size - 1].y2,
                vehicle.path[vehicle.path.size - 1].x2 )
            if (lastPosition != marker.position) {
                MarkerAnimation.animateMarkerToHC(
                    map,
                    marker,
                    vehicle.path,
                    GeoPointInterpolator.Linear(),
                    traveledRoute
                )
            }
        } else {
            MarkerAnimation.animateMarkerToHCLinear(
                map, marker,
                ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude),
                GeoPointInterpolator.Linear(),
                traveledRoute
            )
            marker.rotation = fullAngle - vehicle.heading
        }
    }


    private fun fillMarkerData(marker: Marker, icon: Drawable, typeVehicle:String, title:String){
        marker.icon=icon
        marker.id=typeVehicle
        marker.title=title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    }




    private fun drawMarkerVehiclesOnMap(vehicle: Vehicle, map : MapView) {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = Marker(map)
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()
        if (vehicle.category == typeVehicleBus) {
            fillMarkerData(marker,drawables.busIconDrawable,typeVehicleBus,vehicle.name)
        } else {

            fillMarkerData(marker,drawables.tramIconDrawable,typeVehicleTram,vehicle.name)
        }

        markers[vehicle.id] = marker
        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            markerTracing.showInfoWindow()
            drawPathVehicle(vehicle.id, vehicle.category, mapView, marker)
        }
    }



    private fun analisePathVehicleResponse(response: Response<JsonObject>, map: MapView, marker: Marker){
        val FIRST_RESPONSE_ELEM=0
        val PATH="paths"
        val WAY_POINT="wayPoints"
        val LATITUDE="lat"
        val LONGITUDE="lon"


        Log.i("ERRORR2", response.raw().toString())
        val geoPoints = ArrayList<GeoPoint>()
        val jsonObjectValue = response.body()!!
        jsonObjectValue.getAsJsonArray(PATH)[FIRST_RESPONSE_ELEM]
            .asJsonObject.getAsJsonArray(WAY_POINT)
            .forEach {
                geoPoints.add(
                    ConvertUnits.convertToGeoPoint(
                        it.asJsonObject[LATITUDE].asLong,
                        it.asJsonObject[LONGITUDE].asLong
                    )
                )
            }
        drawPathVehicleOnMap(map, marker, geoPoints)
    }

    private fun drawPathVehicle(idVehicle: String, type: String, map: MapView, marker: Marker) : Boolean{

        if (type == typeVehicleTram) {
            Api.getApi().getTramPath(idVehicle,fun( response: Response<JsonObject>) {
               analisePathVehicleResponse(response,map,marker)
            })
        } else {
            Api.getApi().getBusPath(idVehicle,fun( response: Response<JsonObject>) {
                analisePathVehicleResponse(response,map,marker)
            })

        }

        } )
        return true
    }

    private fun drawPathVehicleOnMap(map: MapView, marker: Marker,
                                pathPoints : ArrayList<GeoPoint>
    ) {
        if (trackingVehicle != null) {

            if (trackingVehicle!!.id == typeVehicleBusFocused) {
                trackingVehicle!!.icon = drawables.resizedBusIcon
            } else {
                trackingVehicle!!.icon = drawables.resizedTramIcon
            }
        }
        trackingVehicle = marker
        if (marker.id == typeVehicleBus) {
            marker.id = "busFocused"
            marker.icon = drawables.resizedBusIconTracking
        } else {
            marker.id = "tramFocused"
            marker.icon = drawables.resizedTramIconTracking
        }
        trackedRoute.actualPoints.clear()
        traveledRoute.actualPoints.clear()
        pathPoints.forEach {
            if (pathPoints[0].distanceToAsDouble(it) < pathPoints[0].distanceToAsDouble(marker.position)) {
                traveledRoute.addPoint(it)
            } else {
                trackedRoute.addPoint(it)
            }

        }
        map.overlays.add(traveledRoute)
        map.invalidate()
    }

    fun createPolyline(tracked: Polyline, traveled: Polyline, width: Float, color: String) {
        trackedRoute = tracked
        trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        trackedRoute.outlinePaint.color = Color.parseColor(color)
        trackedRoute.outlinePaint.strokeWidth = width
        trackedRoute.isGeodesic = true

        traveledRoute = traveled
        traveledRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        traveledRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        traveledRoute.outlinePaint.color = Color.parseColor("#FF0000")
        traveledRoute.outlinePaint.strokeWidth = width
        traveledRoute.isGeodesic = true
    }

    fun getActualPosition(map : MapView){
      //  combine: (responseFun: Response<AllVehicles>) -> Unit
      Api.getApi().getBusPosition(lastUpdateBus,
          fun(response: Response<AllVehicles>) {
              if (response.isSuccessful && response.body() != null) {
                  val allBus = response.body()!!
                  lastUpdateBus = allBus.lastUpdate
                  showAllVehicle(map, response.body()!!)
                  Log.i("ERRORR2", allBus.toString())
              }
          }
        )

                override fun onFailure(call: Call<AllVehicles>, t: Throwable) {

      Api.getApi().getTramPosition(lastUpdateTram, fun(response: Response<AllVehicles>)  {
          if (response.isSuccessful) {
              val allTram = response.body()!!
              lastUpdateTram = allTram.lastUpdate
              showAllVehicle(map, allTram)
          }
      })

    }
}