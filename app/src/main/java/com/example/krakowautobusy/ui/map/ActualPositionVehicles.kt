package com.example.krakowautobusy.ui.map


import android.content.Context

import android.util.Log
import androidx.core.content.ContextCompat
import com.example.krakowautobusy.R
import android.graphics.drawable.Drawable
import android.os.StrictMode
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import org.json.JSONObject
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

import org.osmdroid.views.overlay.Polyline

import java.net.URL
import java.nio.charset.StandardCharsets
import kotlin.reflect.KProperty

class ActualPositionVehicles {

    private var lastUpdateBus: Long = 0
    private var lastUpdateTram: Long = 0
    private var markers = mutableMapOf<String, Marker>()
    private var trackedRoute = Polyline()
    private val json: Json = Json {
        ignoreUnknownKeys = true
    }
    private val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

    fun showAllVehicle(map: MapView, busIcon: Drawable, tramIcon:Drawable)  {
        val allVehiclesBus = getAllVehicleBus()
        val allVehiclesTram = getAllVehicleTram()
        val listOfAllVehicle = allVehiclesBus.vehicles
        listOfAllVehicle.addAll(allVehiclesTram.vehicles)
        lastUpdateBus = allVehiclesBus.lastUpdate
        lastUpdateTram = allVehiclesTram.lastUpdate
        listOfAllVehicle
            .filter { !it.isDeleted }
            .forEach { it ->
                val locationPoint = GeoPoint(0.0, 0.0)
                if (it.path.isEmpty()) {
                    locationPoint.latitude =  (it.latitude / 3600000f).toDouble()
                    locationPoint.longitude =  (it.longitude / 3600000f).toDouble()
                } else {

                    locationPoint.latitude = (it.path[it.path.size - 1].y2 / 3600000f).toDouble()
                    locationPoint.longitude = (it.path[it.path.size - 1].x2 / 3600000f).toDouble()

                }
                if (markers.containsKey(it.id)) {
                    val mark = markers[it.id]!!
                    mark.position = locationPoint
                    val polyline = Polyline()
                    /*it.path.forEach {
                        polyline.addPoint(GeoPoint(it.y1.toDouble() / 3600000f,
                            it.x1.toDouble() / 3600000f))
                        polyline.addPoint(GeoPoint(it.y2.toDouble() / 3600000f,
                            it.x2.toDouble() / 3600000f))
                    }*/
                    if (it.path.size > 0) {
                        mark.rotation = it.path[it.path.size - 1].angle.toFloat()
                    } else {
                        mark.rotation = it.heading.toFloat()
                    }
                    map.overlays.add(polyline)
                    //MarkerAnimation.animateMarkerToHC(map, mark, locationPoint, GeoPointInterpolator.Linear(), it.heading)

                } else {
                    val marker = Marker(map)
                    marker.position = locationPoint
                    marker.rotation = it.heading.toFloat()
                    if (it.category == "bus") {
                        marker.icon = busIcon
                        marker.id = "bus"
                    } else {
                        marker.icon = tramIcon
                        marker.id = "tram"
                    }
                    marker.title = it.name
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                    markers.put(it.id, marker);
                    map.overlays.add(marker)

                    marker.setOnMarkerClickListener { marker, mapView ->
                        drawPathVehicle(it.id)

                    }




                }
            }
        map.overlays.add(trackedRoute)
        map.invalidate()
    }





    private fun getAllVehicleBus() : AllVehicles {

        StrictMode.setThreadPolicy(policy)
        val apiResponse =
            URL(
                "http://ttss.mpk.krakow.pl/internetservice/geoserviceDispatcher/" +
                        "services/vehicleinfo/vehicles?lastUpdate=${this.lastUpdateBus}&positionType=CORRECTED&colorType=ROUTE_BASED&language=pl"
            ).readText(
                StandardCharsets.UTF_8
            )


        return json.decodeFromString(apiResponse)
    }

    private fun getAllVehicleTram(): AllVehicles {
        StrictMode.setThreadPolicy(policy)
        val apiResponse =
            URL("http://www.ttss.krakow.pl/internetservice/geoserviceDispatcher/" +
                    "services/vehicleinfo/vehicles?lastUpdate=${this.lastUpdateTram}&positionType=CORRECTED&colorType=ROUTE_BASED&language=pl").readText(
                StandardCharsets.UTF_8
            )
        return json.decodeFromString(apiResponse)
    }

    private fun getPathVehicle(idVehicle : String): ArrayList<GeoPoint> {
        StrictMode.setThreadPolicy(policy)
        val apiResponse =
            URL("http://ttss.mpk.krakow.pl/internetservice/" +
                    "geoserviceDispatcher/services/pathinfo/" +
                    "vehicle?id=${idVehicle}").readText(
                StandardCharsets.UTF_8
            )

        val geoPoints = ArrayList<GeoPoint>()
        val jsonObjectValue = json.decodeFromString<JsonObject>(apiResponse)
        jsonObjectValue.getValue("paths").jsonArray[0].
        jsonObject.getValue("wayPoints")
            .jsonArray
            .forEach {
                geoPoints.add(GeoPoint(
                    (it.jsonObject["lat"]!!.jsonPrimitive.float / 3600000f ).toDouble(),
                    (it.jsonObject["lon"]!!.jsonPrimitive.float/ 3600000f ).toDouble())
                )
            }
        return geoPoints
    }

    private fun drawPathVehicle(idVehicle : String) : Boolean {
        val cos = getPathVehicle(idVehicle)
        trackedRoute.actualPoints.clear()
        trackedRoute.actualPoints.addAll(cos)
        Log.i("SIEMA", cos.toString())
        Log.i("SIEMA", cos.size.toString())

        return true
    }

}