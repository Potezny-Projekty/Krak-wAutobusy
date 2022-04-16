package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class Utilities(var context: Context) {
    private var currentZoomLevel = 13

    private var ZOOM_LEVEL_13_ICON_SIZE = 84
    private var ZOOM_LEVEL_14_ICON_SIZE = 90
    private var ZOOM_LEVEL_15_ICON_SIZE = 96
    private var ZOOM_LEVEL_16_ICON_SIZE = 105
    private var ZOOM_LEVEL_17_ICON_SIZE = 120
    private var ZOOM_LEVEL_18_ICON_SIZE = 130
    private var ZOOM_LEVEL_19_ICON_SIZE = 135
    private var ZOOM_LEVEL_20_ICON_SIZE = 150

    fun setZoomLevel(currentZoomLevel: Int) {
        this.currentZoomLevel = currentZoomLevel
    }

    fun resizeDrawable(icon: Drawable): Drawable {
        val bitmap: Bitmap = getBitmapFromVectorDrawable(icon)!!
        val resized = Bitmap.createScaledBitmap(
            bitmap,
            setIconSize(currentZoomLevel),
            setIconSize(currentZoomLevel),
            true
        )
        return BitmapDrawable(context.resources, resized)
    }
    // unsecure conversion from float to int, in this case the lost value is irrelevant
    fun resizeDrawable(icon: Drawable,multiplier : Float): Drawable {
        val bitmap: Bitmap = getBitmapFromVectorDrawable(icon)!!
        val resized = Bitmap.createScaledBitmap(
            bitmap,
            (setIconSize(currentZoomLevel)*multiplier).toInt(),
            (setIconSize(currentZoomLevel)*multiplier).toInt(),
            true
        )
        return BitmapDrawable(context.resources, resized)
    }

    private fun getBitmapFromVectorDrawable(icon: Drawable): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            icon.intrinsicWidth,
            icon.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        icon.setBounds(0, 0, canvas.width, canvas.height)
        icon.draw(canvas)
        return bitmap
    }

    fun setIconSize(zoomLevel: Int): Int {
        var iconSize = ZOOM_LEVEL_14_ICON_SIZE
        when (zoomLevel) {
            13 -> {
                iconSize = ZOOM_LEVEL_13_ICON_SIZE
            }
            14 -> {
                iconSize = ZOOM_LEVEL_14_ICON_SIZE
            }
            15 -> {
                iconSize = ZOOM_LEVEL_15_ICON_SIZE
            }
            16 -> {
                iconSize = ZOOM_LEVEL_16_ICON_SIZE
            }
            17 -> {
                iconSize = ZOOM_LEVEL_17_ICON_SIZE
            }
            18 -> {
                iconSize = ZOOM_LEVEL_18_ICON_SIZE
            }
            19 -> {
                iconSize = ZOOM_LEVEL_19_ICON_SIZE
            }
            20 -> {
                iconSize = ZOOM_LEVEL_20_ICON_SIZE
            }
        }
        return iconSize
    }
}