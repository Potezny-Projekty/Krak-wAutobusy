package com.krak.krakowautobusy.ui.map.vehicledata

import android.animation.ValueAnimator
import androidx.core.animation.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.lang.Exception

object MarkerAnimation {
    private const val DURATION_ANIMATION = 6000L
    private const val START_ANIMATION_RANGE = 0f
    private const val END_ANIMATION_RANGE = 1f

    fun animateMarkerToHC(
        map: MapView,
        marker: VehicleMarker,
        endPoint: ArrayList<PathVehicle>,
        GeoPointInterpolator: GeoPointInterpolator,
        polyline: Polyline,
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
            if (marker.icon == marker.vehicleTrackedIcon ||
                marker.icon == marker.vehicleTrackedIconMirror) {

                try{
                    polyline.addPoint(startPosition)

                }catch (exp:Exception){

                }

            }
            marker.changeMarkerIcon()
            marker.rotation = fullAngle - endPoint[pathIterator].angle
            marker.position = newPosition
            map.invalidate()

        }
        valueAnimator.doOnRepeat {
            pathIterator++
        }

        valueAnimator.setFloatValues(START_ANIMATION_RANGE, END_ANIMATION_RANGE)

        if (endPoint.size == 0) {
            valueAnimator.duration = DURATION_ANIMATION
        } else {
            valueAnimator.duration = DURATION_ANIMATION / endPoint.size

        }
        val lastPositionElement = endPoint.size - 1
        valueAnimator.repeatCount = lastPositionElement
        valueAnimator.start()

        return valueAnimator
    }



}