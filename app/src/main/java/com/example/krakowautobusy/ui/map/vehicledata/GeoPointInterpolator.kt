package com.example.krakowautobusy.ui.map.vehicledata

import org.osmdroid.util.GeoPoint

interface GeoPointInterpolator {
    fun interpolate(fraction: Float, a: GeoPoint, b: GeoPoint): GeoPoint
    class Linear : GeoPointInterpolator {
        override fun interpolate(fraction: Float, a: GeoPoint, b: GeoPoint): GeoPoint {
            val lat = (b.latitude - a.latitude) * fraction + a.latitude
            val lng = (b.longitude - a.longitude) * fraction + a.longitude
            return GeoPoint(lat, lng)
        }
    }
}