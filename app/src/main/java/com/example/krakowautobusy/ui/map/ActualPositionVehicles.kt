package com.example.krakowautobusy.ui.map

import android.content.Context
import android.os.Build
import android.os.StrictMode
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.krakowautobusy.R
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.net.URL
import java.nio.charset.StandardCharsets

class ActualPositionVehicles {
    private var lastUpdate: Long = 0
    private var markers = mutableMapOf<String, Marker>()

    fun showAllVehicle(map: MapView, context: Context?) {
        val allVehicles = getAllVehicleBus()
        val listOfAllVehicle = allVehicles.vehicles
        listOfAllVehicle.addAll(getAllVehicleTram().vehicles)
        if (allVehicles.lastUpdate != lastUpdate) {
            lastUpdate = allVehicles.lastUpdate
            listOfAllVehicle
                .forEach { it ->
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
                                marker.icon =
                                    context?.let {
                                        ContextCompat.getDrawable(
                                            it,
                                            R.drawable.ic_icon_bus
                                        )
                                    }
                            } else {
                                marker.icon =
                                    context?.let {
                                        ContextCompat.getDrawable(
                                            it,
                                            R.drawable.ic_icon_tram
                                        )
                                    }
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
        val json: Json = Json {
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
        val json: Json = Json {
            ignoreUnknownKeys = true
        }

        return json.decodeFromString(apiResponse)
    }
}