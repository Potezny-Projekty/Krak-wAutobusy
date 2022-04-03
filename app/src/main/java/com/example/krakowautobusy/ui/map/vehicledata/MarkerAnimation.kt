package com.example.krakowautobusy.ui.map.vehicledata

import android.animation.ValueAnimator
import com.example.krakowautobusy.ui.map.vehicledata.GeoPointInterpolator
import com.example.krakowautobusy.ui.map.vehicledata.PathVehicle
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


object MarkerAnimation {

    fun animateMarkerToHC(
        map: MapView,
        marker: Marker,
        pathVehicles: ArrayList<PathVehicle>,
        GeoPointInterpolator: GeoPointInterpolator,
    ): ValueAnimator {
        val startPosition = marker.position
        val valueAnimator = ValueAnimator()
        valueAnimator.addUpdateListener { animation ->

        val v = animation.animatedFraction
        //val newPosition: GeoPoint =
          //  GeoPointInterpolator.interpolate(v, pathVehicle)
       // marker.position = newPosition
        map.invalidate()

        }
        valueAnimator.setFloatValues(0f, 1f) // Ignored.
        valueAnimator.duration = 3000
        valueAnimator.start()
        return valueAnimator
    }

}