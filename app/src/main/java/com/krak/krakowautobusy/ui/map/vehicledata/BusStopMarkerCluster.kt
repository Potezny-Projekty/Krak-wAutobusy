package com.krak.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.krak.krakowautobusy.R
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.clustering.StaticCluster
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow

class BusStopMarkerCluster(ctx: Context) : RadiusMarkerClusterer(ctx) {
    private val mDensityDpi = ctx.resources.displayMetrics.densityDpi


    init {
         val clusterIconD: Drawable = AppCompatResources.getDrawable(ctx,
             R.drawable.cluster)!!
        val clusterIcon = (clusterIconD as BitmapDrawable).bitmap
        setIcon(clusterIcon)
        mTextPaint.color = Color.BLACK
    }


    override fun buildClusterMarker(cluster: StaticCluster?, mapView: MapView?): Marker {
        val marker = Marker(mapView)
        marker.position = cluster!!.position
        marker.setInfoWindow(null as MarkerInfoWindow?)
        marker.setAnchor(mAnchorU, mAnchorV)
        val finalIcon = Bitmap.createBitmap(
            mClusterIcon.getScaledWidth(
                mDensityDpi
            ), mClusterIcon.getScaledHeight(mDensityDpi), mClusterIcon.config
        )
        val iconCanvas = Canvas(finalIcon)
        val zeroOffset=0f
        iconCanvas.drawBitmap(mClusterIcon, zeroOffset, zeroOffset, null as Paint?)
        val text = "" + cluster.size
        val textHeight = (mTextPaint.descent() + mTextPaint.ascent()).toInt()
        val textScaleFactor=2.5f

         iconCanvas.drawText(
            text, mTextAnchorU * finalIcon.width.toFloat(), mTextAnchorV * finalIcon.height
                .toFloat() - (textHeight * textScaleFactor).toFloat(), mTextPaint
        )
        marker.icon = BitmapDrawable(mapView!!.context.resources, finalIcon)
        return marker
    }
}