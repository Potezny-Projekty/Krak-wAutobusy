package com.example.krakowautobusy.ui.map.vehicledata

import org.osmdroid.util.GeoPoint

object ConvertUnits {
    fun convertToGeoPoint(latitude : Long, longitude : Long) : GeoPoint {
        val time = 3600000f
        val lat = (latitude / time).toDouble()
        val lon = (longitude / time).toDouble()
        return GeoPoint(lat, lon)
    }
}