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
import com.example.krakowautobusy.ui.map.Drawables
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.clustering.StaticCluster
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow

class BusStopMarkerClusterer(ctx: Context?) : RadiusMarkerClusterer(ctx) {


    override fun buildClusterMarker(cluster: StaticCluster?, mapView: MapView?): Marker {
        val m = Marker(mapView)
        val mDensityDpi = mapView!!.context.resources.displayMetrics.densityDpi
        m.position = cluster!!.position
        m.setInfoWindow(null as MarkerInfoWindow?)
        m.setAnchor(mAnchorU, mAnchorV)
        val clusterIconD: Drawable = AppCompatResources.getDrawable(mapView.context, R.drawable.cluster)!!
        val clusterIcon = (clusterIconD as BitmapDrawable).bitmap

        val finalIcon = Bitmap.createBitmap(
            clusterIcon.getScaledWidth(
                mDensityDpi
            ), clusterIcon.getScaledHeight(mDensityDpi), clusterIcon.config
        )
        val iconCanvas = Canvas(finalIcon)
        iconCanvas.drawBitmap(clusterIcon, 0.0f, 0.0f, null as Paint?)
        val text = "" + cluster!!.size
        val textHeight = (mTextPaint.descent() + mTextPaint.ascent()).toInt()
        mTextPaint.color = Color.BLACK
        mTextPaint.textSize = 35f
        iconCanvas.drawText(
            text, mTextAnchorU * finalIcon.width.toFloat(), mTextAnchorV * finalIcon.height
                .toFloat() - textHeight.toFloat() * 2, mTextPaint
        )
        m.icon = BitmapDrawable(mapView!!.context.resources, finalIcon)
        return m
    }


}

