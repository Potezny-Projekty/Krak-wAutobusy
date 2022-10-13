package com.krak.krakowautobusy

import org.osmdroid.views.overlay.infowindow.InfoWindow
import org.osmdroid.views.MapView
import android.widget.TextView
@Deprecated("No use class")
class BusStopInfoWindow(mapView: MapView, val name : String)
    : InfoWindow(R.layout.activity_bus_stop_info_window, mapView) {

    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)
        val busStopName = mView.findViewById<TextView>(R.id.BusStopName)
        busStopName.text = name
    }

    override fun onClose() {
        closeAllInfoWindowsOn(mapView)
    }
}