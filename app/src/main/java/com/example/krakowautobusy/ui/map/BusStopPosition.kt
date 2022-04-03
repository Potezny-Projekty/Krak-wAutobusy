package com.example.krakowautobusy.ui.map

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.krakowautobusy.database.Database
import com.example.krakowautobusy.database.Select_db_BusStop
import com.example.krakowautobusy.database.Select_db_BusStopInterface
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class BusStopPosition(private var context: Context) {

    private var busStopData: ArrayList<Select_db_BusStopInterface.BusStopRow>
    private var busStopPositionArray: ArrayList<GeoPoint> = ArrayList()

    init {
        busStopData = getBusStopData()
        busStopPositionArray = calculateBusStopPos()
    }

    fun showAllBusStops(icon: Drawable,map: MapView) {
        for ((index, elem) in busStopData.withIndex()) {
            val startingPoint = busStopPositionArray[index]

            val marker = Marker(map)
            marker.position = startingPoint
            marker.icon = icon
            marker.title = elem.nameBusStop + " " + elem.id
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
    private fun getBusStopData(): ArrayList<Select_db_BusStopInterface.BusStopRow> {
        val connection = Select_db_BusStop()
        val instance = Database.getInstance(context)
        return connection.selectBusStopAll(instance.readableDatabase)
    }
}