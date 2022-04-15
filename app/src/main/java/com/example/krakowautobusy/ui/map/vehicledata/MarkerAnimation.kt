package com.example.krakowautobusy.ui.map.vehicledata

import android.animation.ValueAnimator
import androidx.core.animation.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

object MarkerAnimation {
    private const val DURATION_ANIMATION = 5000L

    fun animateMarkerToHC(
        map: MapView,
        marker: Marker,
        endPoint: ArrayList<PathVehicle>,
        GeoPointInterpolator: GeoPointInterpolator
    ): ValueAnimator {

        val valueAnimator = ValueAnimator()
        var i = 0
        val fullAngle = 360F
        valueAnimator.addUpdateListener { animation ->

            val startPosition = marker.position
            val v = animation.animatedFraction
            val start = ConvertUnits.convertToGeoPoint(endPoint[i].y1, endPoint[i].x1)
            val end = ConvertUnits.convertToGeoPoint(endPoint[i].y2, endPoint[i].x2)
            val newPosition: GeoPoint =
                GeoPointInterpolator.interpolate(v, startPosition, end)
            marker.rotation = fullAngle - endPoint[i].angle
            marker.position = newPosition
            map.invalidate()
        }
        valueAnimator.doOnRepeat {
            i++
        }
        valueAnimator.setFloatValues(0f, 1f) // Ignored.
        //Empty array
        if (endPoint.size == 0) {
            valueAnimator.duration = DURATION_ANIMATION
        } else {
            valueAnimator.duration = DURATION_ANIMATION / endPoint.size
        }
        valueAnimator.repeatCount = endPoint.size - 1
        valueAnimator.start()

        return valueAnimator
    }

    fun animateMarkerToHCLinear(
        map: MapView,
        marker: Marker,
        endPoint: GeoPoint,
        GeoPointInterpolator: GeoPointInterpolator
    ): ValueAnimator {
        val valueAnimator = ValueAnimator()
        valueAnimator.addUpdateListener { animation ->
            val startPosition = marker.position
            val v = animation.animatedFraction
            val newPosition: GeoPoint =
                GeoPointInterpolator.interpolate(v, startPosition, endPoint)
            marker.position = newPosition
            map.invalidate()
        }
        valueAnimator.setFloatValues(0f, 1f) // Ignored.
        valueAnimator.duration = DURATION_ANIMATION

        valueAnimator.start()

        return valueAnimator
    }
}