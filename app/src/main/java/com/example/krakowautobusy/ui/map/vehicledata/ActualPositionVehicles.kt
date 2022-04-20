package com.example.krakowautobusy.ui.map.vehicledata


import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.StrictMode
import android.util.Log
import com.example.krakowautobusy.ui.map.Drawables
import com.example.krakowautobusy.ui.map.network.ActualPositionApi
import com.example.krakowautobusy.ui.map.network.RequestActualPosition
import com.example.krakowautobusy.ui.map.network.RetrofitHelperBus
import com.example.krakowautobusy.ui.map.network.RetrofitHelperTram
import com.example.krakowautobusy.ui.map.network.requestData.ActualPositionData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.*
import java.net.URL
import java.nio.charset.StandardCharsets

class ActualPositionVehicles(var drawables: Drawables) {
    private var lastUpdateBus: Long = 0
    private var lastUpdateTram: Long = 0
    private var markers = mutableMapOf<String, Marker>()
    private var trackedRoute = Polyline()
    private val json: Json = Json {
        ignoreUnknownKeys = true
    }

    private var enabled = true
    private var trackingVehicle: Marker? = null
    private val typeVehicleBus = "bus"
    private val typeVehicleTram = "tram"
    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun showAllVehicle(map: MapView) {
        val allVehiclesBus = getAllVehicle(typeVehicleBus)
        val allVehiclesTram = getAllVehicle(typeVehicleTram)
        val listOfAllVehicle = allVehiclesBus.vehicles
        val fullAngle = 360F

        listOfAllVehicle.addAll(allVehiclesTram.vehicles)
        lastUpdateBus = allVehiclesBus.lastUpdate
        lastUpdateTram = allVehiclesTram.lastUpdate
        listOfAllVehicle
            .filter { !it.isDeleted }
            .forEach {
                if (markers.containsKey(it.id)) {
                    val mark = markers[it.id]!!
                    if (it.path.size > 0) {
                        val lastPosition = ConvertUnits.convertToGeoPoint(it.path[it.path.size - 1].y2,
                            it.path[it.path.size - 1].x2 )
                        if (lastPosition != mark.position) {
                            MarkerAnimation.animateMarkerToHC(
                                map,
                                mark,
                                it.path,
                                GeoPointInterpolator.Linear()
                            )
                        }
                    } else {
                        MarkerAnimation.animateMarkerToHCLinear(
                            map, mark,
                            ConvertUnits.convertToGeoPoint(it.latitude, it.longitude),
                            GeoPointInterpolator.Linear()
                        )
                        mark.rotation = fullAngle - it.heading
                    }
                } else {
                    val locationPoint =
                        ConvertUnits.convertToGeoPoint(it.latitude, it.longitude)
                    val marker = Marker(map)
                    marker.position = locationPoint
                    marker.rotation = fullAngle - it.heading.toFloat()
                    if (it.category == typeVehicleBus) {
                        marker.icon = drawables.busIconDrawable
                        marker.id = typeVehicleBus
                    } else {
                        marker.icon = drawables.tramIconDrawable
                        marker.id = typeVehicleTram
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

    private fun getAllVehicle(type: String): AllVehicles {
        val url: String = if (type == typeVehicleTram) {
            "http://www.ttss.krakow.pl/internetservice/geoserviceDispatcher/" +
                    "services/vehicleinfo/vehicles?lastUpdate=${this.lastUpdateTram}" +
                    "&positionType=CORRECTED&colorType=ROUTE_BASED&language=pl"
        } else {
            "http://ttss.mpk.krakow.pl/internetservice/geoserviceDispatcher/" +
                    "services/vehicleinfo/vehicles?lastUpdate=${this.lastUpdateBus}" +
                    "&positionType=CORRECTED&colorType=ROUTE_BASED&language=pl"
        }
        val apiResponse =
            URL(url).readText(
                StandardCharsets.UTF_8
            )
        return json.decodeFromString(apiResponse)
    }

    private fun getPathVehicle(idVehicle: String, type: String): ArrayList<GeoPoint> {
        val url: String = if (type == typeVehicleTram) {
            "http://www.ttss.krakow.pl/internetservice/" +
                    "geoserviceDispatcher/services/pathinfo/vehicle?id=${idVehicle}"
        } else {
            "http://ttss.mpk.krakow.pl/internetservice/" +
                    "geoserviceDispatcher/services/pathinfo/vehicle?id=${idVehicle}"
        }
        val apiResponse =
            URL(url).readText(
                StandardCharsets.UTF_8
            )
        val geoPoints = ArrayList<GeoPoint>()
        val jsonObjectValue = json.decodeFromString<JsonObject>(apiResponse)
        jsonObjectValue.getValue("paths").jsonArray[0].jsonObject.getValue("wayPoints")
            .jsonArray
            .forEach {
                geoPoints.add(
                    ConvertUnits.convertToGeoPoint(
                        it.jsonObject["lat"]!!.jsonPrimitive.long,
                        it.jsonObject["lon"]!!.jsonPrimitive.long
                    )
                )
            }
        return geoPoints
    }

    private fun drawPathVehicle(
        idVehicle: String, type: String,
        map: MapView, marker: Marker
    ): Boolean {
        val pathPoints = getPathVehicle(idVehicle, type)
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
        return true
    }

    fun createPolyline(polyline: Polyline, width: Float, color: String) {
        trackedRoute = polyline
        trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        trackedRoute.outlinePaint.color = Color.parseColor(color)
        trackedRoute.outlinePaint.strokeWidth = width
        trackedRoute.isGeodesic = true
    }

    fun getActualPosition(){
        val busHelperInstance = RetrofitHelperBus.getInstance().create(ActualPositionApi::class.java)
        busHelperInstance.getAllVehicles(ActualPositionData(0)).enqueue(RequestActualPosition)

        val tramHelperInstance = RetrofitHelperTram.getInstance().create(ActualPositionApi::class.java)
        tramHelperInstance.getAllVehicles(ActualPositionData(0)).enqueue(RequestActualPosition)
    }
}