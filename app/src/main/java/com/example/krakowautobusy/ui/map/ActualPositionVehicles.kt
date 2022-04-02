package com.example.krakowautobusy.ui.map

import android.graphics.drawable.Drawable
import android.os.StrictMode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.net.URL
import java.nio.charset.StandardCharsets

class ActualPositionVehicles {
    private var lastUpdate: Long = 0
    var markers = mutableMapOf<String, Marker>()

    fun showAllVehicle(map: MapView, busIcon: Drawable, tramIcon:Drawable): Map<String, Marker> {
        val allVehicles = getAllVehicleBus()
        val listOfAllVehicle = allVehicles.vehicles
        listOfAllVehicle.addAll(getAllVehicleTram().vehicles)
        if (allVehicles.lastUpdate != lastUpdate) {
            lastUpdate = allVehicles.lastUpdate
            listOfAllVehicle
                .forEach {
                    if (!it.isDeleted) {
                        val locationPoint = GeoPoint(
                            (it.latitude / 3600000f).toDouble(),
                            (it.longitude / 3600000f).toDouble()
                        )
                        if (markers.containsKey(it.id)) {
                            val mark = markers.get(it.id)!!
                            mark.position = locationPoint
                            mark.rotation = it.heading.toFloat()
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
                        }
                    }
                }
            map.invalidate()
        }
        return markers
    }

    private fun getAllVehicleBus(): AllVehicles {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val apiResponse =
            URL(
                "http://ttss.mpk.krakow.pl/internetservice/geoserviceDispatcher/" +
                        "services/vehicleinfo/vehicles"
            ).readText(
                StandardCharsets.UTF_8
            )
        val json = Json {
            ignoreUnknownKeys = true
        }

        return json.decodeFromString(apiResponse)
    }
    private fun getAllVehicleTram(): AllVehicles {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val apiResponse =
            URL(
                "http://www.ttss.krakow.pl/internetservice/geoserviceDispatcher/" +
                        "services/vehicleinfo/vehicles"
            ).readText(
                StandardCharsets.UTF_8
            )
        val json = Json {
            ignoreUnknownKeys = true
        }

        return json.decodeFromString(apiResponse)
    }
}