package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
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
        m.position = cluster!!.position
        m.setInfoWindow(null as MarkerInfoWindow?)
        m.setAnchor(mAnchorU, mAnchorV)
        if (mapView != null) {
            m.icon = AppCompatResources.getDrawable(mapView.context , R.drawable.ic_bus_stop)
        }
        return m
    }
}