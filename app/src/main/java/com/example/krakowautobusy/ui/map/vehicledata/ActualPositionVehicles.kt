package com.example.krakowautobusy.ui.map.vehicledata

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
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
    private val busHelperInstance = RetrofitHelperBus
        .getInstance().create(ActualPositionApi::class.java)
    private val tramHelperInstance = RetrofitHelperTram.getInstance()
        .create(ActualPositionApi::class.java)
    private val fullAngle = 360F
    private var enabled = true
    private var trackingVehicle: Marker? = null
    private val typeVehicleBus = "bus"
    private val typeVehicleTram = "tram"

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
        if (vehicle.path.size > 0) {
            val lastPosition = ConvertUnits.convertToGeoPoint(vehicle.path[vehicle.path.size - 1].y2,
                vehicle.path[vehicle.path.size - 1].x2 )
            if (lastPosition != marker.position) {
                MarkerAnimation.animateMarkerToHC(
                    map,
                    marker,
                    vehicle.path,
                    GeoPointInterpolator.Linear()
                )
            }
        } else {
            MarkerAnimation.animateMarkerToHCLinear(
                map, marker,
                ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude),
                GeoPointInterpolator.Linear()
            )
            marker.rotation = fullAngle - vehicle.heading
        }
    }

    private fun drawMarkerVehiclesOnMap(vehicle: Vehicle, map : MapView) {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = Marker(map)
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()
        if (vehicle.category == typeVehicleBus) {
            marker.icon = drawables.busIconDrawable
            marker.id = typeVehicleBus
        } else {
            marker.icon = drawables.tramIconDrawable
            marker.id = typeVehicleTram
        }
        marker.title = vehicle.name
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        markers[vehicle.id] = marker
        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            markerTracing.showInfoWindow()
            drawPathVehicle(vehicle.id, vehicle.category, mapView, marker)
        }
    }

    private fun drawPathVehicle(idVehicle: String, type: String, map: MapView, marker: Marker) : Boolean{
        val pathVehicleAPi =  if (type == typeVehicleTram) {
            tramHelperInstance
        } else {
            busHelperInstance
        }
        pathVehicleAPi.getPathVehicle(idVehicle).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>,
                                    response: Response<JsonObject>) {
                Log.i("ERRORR2", response.raw().toString())
                val geoPoints = ArrayList<GeoPoint>()
                val jsonObjectValue = response.body()!!
                jsonObjectValue.getAsJsonArray("paths")[0]
                    .asJsonObject.getAsJsonArray("wayPoints")
                    .forEach {
                        geoPoints.add(
                            ConvertUnits.convertToGeoPoint(
                                it.asJsonObject["lat"].asLong,
                                it.asJsonObject["lon"].asLong
                            )
                        )
                    }
                drawPathVehicleOnMap(map, marker, geoPoints)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.i("ERRORR2", t.toString())
                Log.i("ERRORR2", call.request().toString())
            }

        } )
        return true
    }

    private fun drawPathVehicleOnMap(map: MapView, marker: Marker,
                                pathPoints : ArrayList<GeoPoint>
    ) {
        if (trackingVehicle != null) {
            if (trackingVehicle!!.id == typeVehicleBus) {
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
        pathPoints.forEach {
            trackedRoute.addPoint(it)
        }
        map.invalidate()
    }

    fun createPolyline(polyline: Polyline, width: Float, color: String) {
        trackedRoute = polyline
        trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        trackedRoute.outlinePaint.color = Color.parseColor(color)
        trackedRoute.outlinePaint.strokeWidth = width
        trackedRoute.isGeodesic = true
    }

    fun getActualPosition(map : MapView){
        busHelperInstance.getAllVehicles(lastUpdateBus)
            .enqueue(object:
            Callback<AllVehicles> {
                override fun onResponse(call: Call<AllVehicles>, response: Response<AllVehicles>) {
                    if (response.isSuccessful) {
                        val allBus = response.body()!!
                        lastUpdateBus = allBus.lastUpdate
                        showAllVehicle(map, response.body()!!)
                        Log.i("ERRORR2", allBus.toString())
                    }
                }

                override fun onFailure(call: Call<AllVehicles>, t: Throwable) {

                }
        })
        tramHelperInstance.getAllVehicles(lastUpdateTram)
            .enqueue(object: Callback<AllVehicles> {
                override fun onResponse(call: Call<AllVehicles>, response: Response<AllVehicles>) {
                    if (response.isSuccessful) {
                        val allTram = response.body()!!
                        lastUpdateTram = allTram.lastUpdate
                        showAllVehicle(map, allTram)
                    }
                }
                override fun onFailure(call: Call<AllVehicles>, t: Throwable) {
                    Log.i("ERRORR2", t.toString())
                }
        })
    }
}