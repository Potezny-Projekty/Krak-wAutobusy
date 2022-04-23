package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.krakowautobusy.database.Database
import com.example.krakowautobusy.database.VehicleStop
import com.example.krakowautobusy.database.VehicleStopData
import com.example.krakowautobusy.database.VehicleStopInterface
import com.example.krakowautobusy.ui.map.Drawables

import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class BusStopPosition(private var context: Context) {

    private var busStopData: ArrayList<VehicleStopData>
    private var busStopPositionArray: ArrayList<GeoPoint> = ArrayList()

    init {
        busStopData = getBusStopData()
        busStopPositionArray = calculateBusStopPos()
    }

    fun showAllBusStops(map: MapView,icon: Drawables) {
        for ((index, elem) in busStopData.withIndex()) {
            val startingPoint = busStopPositionArray[index]

            val marker = Marker(map)
            marker.position = startingPoint
            marker.icon = icon.busStopIconDrawable
            marker.title = elem.name + " " + elem.idShort
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            marker.id = "busStop"
            map.overlays.add(marker)
        }
        map.invalidate()
    }

    private fun calculateBusStopPos(): ArrayList<GeoPoint> {
        for (elem in busStopData) {
            busStopPositionArray.add(
                GeoPoint(
                    (elem.latitude / 3600000f).toDouble(),
                    (elem.longitude / 3600000f).toDouble()
                )
            )
        }
        return busStopPositionArray
    }

    fun showBusStop(icon: Drawable, latitude: Double, longtitude: Double,map: MapView) {
        val startingPoint = GeoPoint(latitude, longtitude)

        val marker = Marker(map)
        marker.position = startingPoint
        marker.icon = icon
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)

        map.invalidate()
    }
    private fun getBusStopData(): ArrayList<VehicleStopData> {
        val connection = VehicleStop()
        val instance = Database.getInstance(context)
        return connection.getAllVehicleStop(instance.readableDatabase)
    }
}