package com.example.krakowautobusy.ui.map

import android.content.Context
import android.graphics.*
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.krakowautobusy.R
import com.example.krakowautobusy.ui.map.vehicledata.Utilities


class Drawables(var context: Context) {
    var busStopIconDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.bus_icon)!!
    var tramIconDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_tram_icon)!!
    var tramIconMirrorDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_tram_icon_mirror)!!
    var busIconDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_bus_icon)!!
    var busIconMirrorDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_bus_icon_mirror)!!
    var userLocationIconDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.location_icon)!!
    var busIconTrackingDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_bus_tracking)!!
    var busIconTrackingMirrorDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_bus_tracking_mirror)!!
    var tramIconTrackingDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_tram_tracking)!!
    var tramIconTrackingMirrorDrawable: Drawable = AppCompatResources.getDrawable(context, R.drawable.ic_icon_tram_tracking_mirror)!!
    var vehicleStopIcon:Drawable= AppCompatResources.getDrawable(context, R.drawable.przytsanek)!!

    lateinit var resizedBusStopIcon: Drawable
    lateinit var resizedTramIcon: Drawable
    lateinit var resizedBusIcon: Drawable
    lateinit var resizedUserLocationIcon: Drawable
    lateinit var resizedBusIconTracking: Drawable
    lateinit var resizedTramIconTracking: Drawable

    private var BUS_STOP_ICON_SIZE_MULTIPLIER = 0.33f

    fun resizeIcons(drawables: Drawables, utilities: Utilities, zoomLevel: Int) {
        utilities.setZoomLevel(zoomLevel)
        drawables.resizedBusStopIcon = utilities.resizeDrawable(drawables.busStopIconDrawable,BUS_STOP_ICON_SIZE_MULTIPLIER)
        drawables.resizedBusIcon = utilities.resizeDrawable(drawables.busIconDrawable)
        drawables.resizedTramIcon = utilities.resizeDrawable(drawables.tramIconDrawable)
        drawables.resizedUserLocationIcon = utilities.resizeDrawable(drawables.userLocationIconDrawable)
        drawables.resizedBusIconTracking = utilities.resizeDrawable(drawables.busIconTrackingDrawable)
        drawables.resizedTramIconTracking = utilities.resizeDrawable(drawables.tramIconTrackingDrawable)
    }
    
}