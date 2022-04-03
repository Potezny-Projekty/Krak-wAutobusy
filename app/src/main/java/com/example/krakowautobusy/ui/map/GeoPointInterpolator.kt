package com.example.krakowautobusy.ui.map

import org.osmdroid.util.GeoPoint



interface GeoPointInterpolator {
    fun interpolate(fraction: Float, pathVehicle: PathVehicle): GeoPoint
    class Linear : GeoPointInterpolator {
        override fun interpolate(fraction: Float, pathVehicle: PathVehicle): GeoPoint {
           // val lat = (b.latitude - a.latitude) * fraction + a.latitude
            //val lng = (b.longitude - a.longitude) * fraction + a.longitude
            return GeoPoint(0.0, 0.0)
        }

    }

  /*  class LinearFixed : GeoPointInterpolator {
        override fun interpolate(fraction: Float, a: GeoPoint, b: GeoPoint): GeoPoint {
            val lat = (b.latitude - a.latitude) * fraction + a.latitude
            var lngDelta = b.longitude - a.longitude

            // Take the shortest path across the 180th meridian.
            if (Math.abs(lngDelta) > 180) {
                lngDelta -= Math.signum(lngDelta) * 360
            }
            val lng = lngDelta * fraction + a.longitude
            return GeoPoint(lat, lng)
        }
    }

    class Spherical : GeoPointInterpolator {
        *//* From github.com/googlemaps/android-maps-utils *//*
        override fun interpolate(fraction: Float, from: GeoPoint, to: GeoPoint): GeoPoint {
            // http://en.wikipedia.org/wiki/Slerp
            val fromLat = Math.toRadians(from.latitude)
            val fromLng = Math.toRadians(from.longitude)
            val toLat = Math.toRadians(to.latitude)
            val toLng = Math.toRadians(to.longitude)
            val cosFromLat = Math.cos(fromLat)
            val cosToLat = Math.cos(toLat)

            // Computes Spherical interpolation coefficients.
            val angle = computeAngleBetween(fromLat, fromLng, toLat, toLng)
            val sinAngle = Math.sin(angle)
            if (sinAngle < 1E-6) {
                return from
            }
            val a = Math.sin((1 - fraction) * angle) / sinAngle
            val b = Math.sin(fraction * angle) / sinAngle

            // Converts from polar to vector and interpolate.
            val x = a * cosFromLat * Math.cos(fromLng) + b * cosToLat * Math.cos(toLng)
            val y = a * cosFromLat * Math.sin(fromLng) + b * cosToLat * Math.sin(toLng)
            val z = a * Math.sin(fromLat) + b * Math.sin(toLat)

            // Converts interpolated vector back to polar.
            val lat = Math.atan2(z, Math.sqrt(x * x + y * y))
            val lng = Math.atan2(y, x)
            return GeoPoint(Math.toDegrees(lat), Math.toDegrees(lng))
        }

        private fun computeAngleBetween(
            fromLat: Double,
            fromLng: Double,
            toLat: Double,
            toLng: Double
        ): Double {
            // Haversine's formula
            val dLat = fromLat - toLat
            val dLng = fromLng - toLng
            return 2 * Math.asin(
                Math.sqrt(
                    Math.pow(Math.sin(dLat / 2), 2.0) +
                            Math.cos(fromLat) * Math.cos(toLat) * Math.pow(Math.sin(dLng / 2), 2.0)
                )
            )
        }
    }*/
}