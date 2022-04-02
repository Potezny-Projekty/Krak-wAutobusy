package com.example.krakowautobusy.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class Utilities {

    fun resizeDrawable(x: Int, y: Int, icon: Drawable, context: Context): Drawable {
        val bitmap: Bitmap = getBitmapFromVectorDrawable(icon)!!
        val resized = Bitmap.createScaledBitmap(bitmap, x, y, true)
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
        var iconSize = 15
        when (zoomLevel) {
            13 -> {
                iconSize = 15
            }
            14 -> {
                iconSize = 18
            }
            15 -> {
                iconSize = 25
            }
            16 -> {
                iconSize = 30
            }
            17 -> {
                iconSize = 35
            }
            18 -> {
                iconSize = 40
            }
            19 -> {
                iconSize = 45
            }
            20 -> {
                iconSize = 50
            }
        }
        return iconSize
    }
}