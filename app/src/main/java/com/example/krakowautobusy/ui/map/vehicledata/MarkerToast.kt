package com.example.krakowautobusy.ui.map.vehicledata


import com.example.krakowautobusy.R
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow


class MarkerToast(mapView: MapView)
    : InfoWindow(R.layout.activity_marker_toast, mapView) {

    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)
    }

    override fun onClose() {
        closeAllInfoWindowsOn(mapView)
    }
}