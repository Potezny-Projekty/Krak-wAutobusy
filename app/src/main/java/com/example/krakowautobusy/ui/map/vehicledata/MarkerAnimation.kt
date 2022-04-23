package com.example.krakowautobusy.ui.map.vehicledata

import android.animation.ValueAnimator
import androidx.core.animation.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

object MarkerAnimation {
    private const val DURATION_ANIMATION = 6000L
    private const val START_ANIMATION_RANGE = 0f
    private const val END_ANIMATION_RANGE = 1f

    fun animateMarkerToHC(
        map: MapView,
        marker: Marker,
        endPoint: ArrayList<PathVehicle>,
        GeoPointInterpolator: GeoPointInterpolator
    ): ValueAnimator {

        val valueAnimator = ValueAnimator()
        var pathIterator = 0
        val fullAngle = 360F
        valueAnimator.addUpdateListener { animation ->

            val startPosition = marker.position
            val fraction = animation.animatedFraction
            val end = ConvertUnits.convertToGeoPoint(endPoint[pathIterator].y2, endPoint[pathIterator].x2)
            val newPosition: GeoPoint =
                GeoPointInterpolator.interpolate(fraction, startPosition, end)
            marker.rotation = fullAngle - endPoint[pathIterator].angle
            marker.position = newPosition
            map.invalidate()
        }
        valueAnimator.doOnRepeat {
            pathIterator++
        }

        valueAnimator.setFloatValues(START_ANIMATION_RANGE, END_ANIMATION_RANGE)
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
            val fraction = animation.animatedFraction
            val newPosition: GeoPoint =
                GeoPointInterpolator.interpolate(fraction, startPosition, endPoint)
            marker.position = newPosition
            map.invalidate()
        }
        valueAnimator.setFloatValues(START_ANIMATION_RANGE, END_ANIMATION_RANGE)
        valueAnimator.duration = DURATION_ANIMATION

        valueAnimator.start()

        return valueAnimator
    }
}