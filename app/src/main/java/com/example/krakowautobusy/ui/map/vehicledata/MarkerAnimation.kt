package com.example.krakowautobusy.ui.map.vehicledata


import android.animation.Animator
import android.animation.FloatArrayEvaluator
import android.animation.ValueAnimator
import android.util.Log
import androidx.core.animation.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


object MarkerAnimation {
    private val DURATION_ANIMATION = 5000L

    fun animateMarkerToHC(
        map: MapView,
        marker: Marker,
        endPoint: ArrayList<PathVehicle>,
        GeoPointInterpolator: GeoPointInterpolator
    ): ValueAnimator {

        val valueAnimator = ValueAnimator()
        var i = 0
        valueAnimator.addUpdateListener { animation ->

            val startPosition = marker.position
            val v = animation.animatedFraction
            val start = GeoPoint((endPoint[i].y1 / 3600000f).toDouble(),
                (endPoint[i].x1 / 3600000f).toDouble())
            val end = GeoPoint((endPoint[i].y2 / 3600000f).toDouble(),
                (endPoint[i].x2 / 3600000f).toDouble())
            val newPosition: GeoPoint =
            GeoPointInterpolator.interpolate(v, startPosition, end)
            marker.rotation = 360f - endPoint[i].angle
            marker.position = newPosition
            map.invalidate()
        }
        valueAnimator.doOnRepeat {
            i++
        }
        valueAnimator.setFloatValues(0f, 1f) // Ignored.
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