package com.example.krakowautobusy.ui.map.vehicledata

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.graphics.drawable.toDrawable
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class VehicleMarker(mapView: MapView?, val vehicle : Vehicle) : Marker(mapView) {
    var isMirror : Boolean = false
    var isTracked : Boolean = false
    var vehicleIcon : Drawable = icon
    var vehicleIconMirror : Drawable = icon
    var vehicleTrackedIcon : Drawable = icon
    var vehicleTrackedIconMirror : Drawable = icon
    private val textSize = 24f

    fun mirrorMarkerIcon(icon : Drawable, number : String) : Drawable {
        val copyIcon = icon.mutate()
        val paint = Paint()
        val factoryMoveHeightText = 2
        val factoryMoveWidthText = 1.43
        val startPositionXText =  ((copyIcon.intrinsicHeight / factoryMoveHeightText)).toFloat()
        val startPositionYText = ((copyIcon.intrinsicWidth / factoryMoveWidthText) * -1).toFloat()
        val rotateCanvasToVerticle = 90f
        paint.color = Color.BLACK
        paint.textSize = textSize
        paint.textAlign = Paint.Align.CENTER
        val bitmap = Bitmap.createBitmap(
            copyIcon.intrinsicWidth,
            copyIcon.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        copyIcon.draw(canvas)
        canvas.rotate(rotateCanvasToVerticle)
        canvas.drawText(number, startPositionXText,
            startPositionYText, paint)
        copyIcon.setBounds(0, 0, canvas.width, canvas.height)
        return bitmap.toDrawable(Resources.getSystem())
    }

    private fun drawNumberOnIcon(icon : Drawable, number : String) : Drawable {
        val copyIcon = icon.mutate()
        val paint = Paint()
        val factoryMoveHeightText = 2
        val factoryMoveWidthText = 3.3
        val startPositionXText =  ((copyIcon.intrinsicHeight / factoryMoveHeightText) * -1).toFloat()
        val startPositionYText = ((copyIcon.intrinsicWidth / factoryMoveWidthText)).toFloat()
        val rotateCanvasToVerticle = -90f
        paint.color = Color.BLACK
        paint.textSize = textSize
        paint.textAlign = Paint.Align.CENTER
        val bitmap = Bitmap.createBitmap(
            copyIcon.intrinsicWidth,
            copyIcon.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        copyIcon.draw(canvas)
        canvas.rotate(rotateCanvasToVerticle)
        canvas.drawText(number, startPositionXText,
            startPositionYText, paint)
        copyIcon.setBounds(0, 0, canvas.width, canvas.height)
        return bitmap.toDrawable(Resources.getSystem())
    }

    fun setVehicleIcon(icon : Drawable, number : String) {
        vehicleIcon = drawNumberOnIcon(icon, number)
    }

    fun setVehicleIconMirror(icon : Drawable, number : String) {
        vehicleIconMirror = mirrorMarkerIcon(icon, number)
    }

    fun setVehicleTrackedIcon(icon : Drawable, number : String) {
        vehicleTrackedIcon = drawNumberOnIcon(icon, number)
    }

    fun setVehicleTrackedIconMirror(icon : Drawable, number : String) {
        vehicleTrackedIconMirror = mirrorMarkerIcon(icon, number)
    }


    fun changeMarkerIcon() {
        val halfAngle = 180
        if (isTracked) {
            if (rotation < halfAngle && !isMirror) {
                icon = vehicleTrackedIconMirror
                isMirror = true
            } else if (rotation > halfAngle && isMirror) {
                icon = vehicleTrackedIcon
                isMirror = false
            }
        } else {
            if (rotation < halfAngle && !isMirror) {
                icon = vehicleIconMirror
                isMirror = true
            } else if (rotation > halfAngle && isMirror) {
                icon = vehicleIcon
                isMirror = false
            }
        }
    }

    fun switchedBetweenTrackingAndStandardIcon() {
       when(icon) {
           vehicleIcon -> {
               icon = vehicleTrackedIcon
               isTracked = true
           }
           vehicleIconMirror -> {
               icon = vehicleTrackedIconMirror
               isTracked = true
           }
           vehicleTrackedIcon -> {
               icon = vehicleIcon
               isTracked = false
           }
           vehicleTrackedIconMirror -> {
               icon = vehicleIconMirror
               isTracked = false
           }
       }
    }
}