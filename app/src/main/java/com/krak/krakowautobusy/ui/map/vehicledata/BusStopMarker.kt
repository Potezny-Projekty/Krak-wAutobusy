package com.krak.krakowautobusy.ui.map.vehicledata

import com.krak.krakowautobusy.database.VehicleStopData
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class BusStopMarker(mapView: MapView?,
                    val busStop : VehicleStopData) : Marker(mapView) {
}