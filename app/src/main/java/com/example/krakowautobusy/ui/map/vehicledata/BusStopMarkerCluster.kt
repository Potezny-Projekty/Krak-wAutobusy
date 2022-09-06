package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.krakowautobusy.R
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.clustering.StaticCluster
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow

class BusStopMarkerCluster(ctx: Context) : RadiusMarkerClusterer(ctx) {
    private val mDensityDpi = ctx.resources.displayMetrics.densityDpi;


    init {
        val clusterIconD: Drawable = AppCompatResources.getDrawable(ctx,
            R.drawable.cluster_icon_bus_stop)!!
       /* val clusterIconD: Drawable = AppCompatResources.getDrawable(ctx,
            R.drawable.cluster)!!*/
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
        iconCanvas.drawBitmap(mClusterIcon, 0.0f, 0.0f, null as Paint?)
        val text = "" + cluster!!.size
        val textHeight = (mTextPaint.descent() + mTextPaint.ascent()).toInt()
        iconCanvas.drawText(
            text, mTextAnchorU * finalIcon.width.toFloat(), mTextAnchorV * finalIcon.height
                .toFloat() + (textHeight / 2).toFloat(), mTextPaint
        )
        /* iconCanvas.drawText(
            text, mTextAnchorU * finalIcon.width.toFloat(), mTextAnchorV * finalIcon.height
                .toFloat() - (textHeight * 2.5).toFloat(), mTextPaint
        )*/
        marker.icon = BitmapDrawable(mapView!!.context.resources, finalIcon)
        return marker
    }
}