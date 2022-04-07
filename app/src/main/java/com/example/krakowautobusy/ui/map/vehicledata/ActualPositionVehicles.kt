package com.example.krakowautobusy.ui.map.vehicledata


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.StrictMode
import android.util.Log
import androidx.core.animation.addListener
import com.example.krakowautobusy.R
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.*
import java.net.URL
import java.nio.charset.StandardCharsets


class ActualPositionVehicles(
    private val busIcon: Drawable, private val tramIcon: Drawable,
    private val busIconTracking: Drawable, private val tramIconTracking: Drawable
) {

    private var lastUpdateBus: Long = 0
    private var lastUpdateTram: Long = 0
    private var markers = mutableMapOf<String, Marker>()
    private var trackedRoute = Polyline()
    private val json: Json = Json {
        ignoreUnknownKeys = true
    }
    private val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    private var trackingVehicle : Marker? = null


    fun showAllVehicle(map: MapView)  {
        val allVehiclesBus = getAllVehicle("bus")
        val allVehiclesTram = getAllVehicle("tram")

        val listOfAllVehicle = allVehiclesBus.vehicles
        listOfAllVehicle.addAll(allVehiclesTram.vehicles)
        lastUpdateBus = allVehiclesBus.lastUpdate
        lastUpdateTram = allVehiclesTram.lastUpdate
        listOfAllVehicle
            .filter { !it.isDeleted }
            .forEach {
                if (markers.containsKey(it.id)) {
                    val mark = markers[it.id]!!
                    if (it.path.size > 0 ) {
                        val point = GeoPoint((it.path[0].y2 / 3600000f).toDouble(),
                            (it.path[0].x2 / 3600000f).toDouble())
                        MarkerAnimation.animateMarkerToHC(map, mark, it.path, GeoPointInterpolator.Linear())
                       /* mark.position = GeoPoint((it.path[it.path.size - 1].y2 / 3600000f).toDouble(),
                            (it.path[it.path.size - 1].x2 / 3600000f).toDouble())*/
                    } else {
                        MarkerAnimation.animateMarkerToHCLinear(map, mark, GeoPoint((it.latitude / 3600000f).toDouble(),
                            (it.longitude / 3600000f).toDouble()), GeoPointInterpolator.Linear())
                        mark.rotation = 360F - it.heading
                    }
                } else {
                    val locationPoint = GeoPoint((it.latitude / 3600000f).toDouble(),
                        (it.longitude / 3600000f).toDouble())
                    val marker = Marker(map)
                    marker.position = locationPoint
                    marker.rotation = 360f - it.heading.toFloat()
                    if (it.category == "bus") {
                        marker.icon = busIcon
                        marker.id = "bus"
                    } else {
                        marker.icon = tramIcon
                        marker.id = "tram"
                    }
                    marker.id = it.category
                    marker.title = it.name
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                    markers[it.id] = marker;
                    map.overlays.add(marker)
                    marker.setOnMarkerClickListener { markerTracing, mapView ->
                        markerTracing.showInfoWindow()
                        drawPathVehicle(it.id, it.category, mapView, marker)
                    }
                }
            }
    }


    private fun getAllVehicle(type : String): AllVehicles {
        val url : String
        if (type == "tram") {
            url = "http://www.ttss.krakow.pl/internetservice/geoserviceDispatcher/" +
            "services/vehicleinfo/vehicles?lastUpdate=${this.lastUpdateTram}" +
                    "&positionType=CORRECTED&colorType=ROUTE_BASED&language=pl"
        } else {
            url = "http://ttss.mpk.krakow.pl/internetservice/geoserviceDispatcher/" +
                    "services/vehicleinfo/vehicles?lastUpdate=${this.lastUpdateBus}" +
                    "&positionType=CORRECTED&colorType=ROUTE_BASED&language=pl"
        }
        val apiResponse =
            URL(url).readText(
                StandardCharsets.UTF_8
            )
        return json.decodeFromString(apiResponse)
    }

    private fun getPathVehicle(idVehicle : String, type : String): ArrayList<GeoPoint> {
        val url : String
        if  (type == "tram") {
            url = "http://www.ttss.krakow.pl/internetservice/" +
                    "geoserviceDispatcher/services/pathinfo/vehicle?id=${idVehicle}"
        } else {
            url = "http://ttss.mpk.krakow.pl/internetservice/" +
                    "geoserviceDispatcher/services/pathinfo/vehicle?id=${idVehicle}"
        }
        val apiResponse =
            URL(url).readText(
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

    private fun drawPathVehicle(idVehicle : String, type: String, map: MapView, marker: Marker) : Boolean {
        val pathPoints = getPathVehicle(idVehicle, type)
        if (trackingVehicle != null) {
            if (trackingVehicle!!.id == "bus") {
                trackingVehicle!!.icon = busIcon
            } else {
                trackingVehicle!!.icon = tramIcon
            }
        }
        trackingVehicle = marker
        if (marker.id == "bus") {
            marker.icon = busIconTracking
        } else {
            marker.icon = tramIconTracking
        }
        trackedRoute.actualPoints.clear()
        pathPoints.forEach {
            trackedRoute.addPoint(it)
        }
        map.invalidate()
        return true
    }

     fun createPolyline(polyline: Polyline) {
         trackedRoute = polyline
         trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
         trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
         trackedRoute.outlinePaint.color = Color.parseColor("#39DD00")
         trackedRoute.outlinePaint.strokeWidth = 20.5f
         trackedRoute.isGeodesic = true
    }
}