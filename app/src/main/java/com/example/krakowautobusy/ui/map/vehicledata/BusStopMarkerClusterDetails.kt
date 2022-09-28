package com.example.krakowautobusy.ui.map.vehicledata

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.krakowautobusy.R
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.bonuspack.clustering.StaticCluster
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow

class BusStopMarkerClusterDetails(ctx: Context) : RadiusMarkerClusterer(ctx) {
    val icon : Drawable = AppCompatResources.getDrawable(ctx,
        R.drawable.ic_cluster)!!

    override fun buildClusterMarker(cluster: StaticCluster?, mapView: MapView?): Marker {
        val marker = Marker(mapView)
        marker.position = cluster!!.position
        marker.setInfoWindow(null as MarkerInfoWindow?)
        marker.setAnchor(mAnchorU, mAnchorV)
        marker.icon = icon
        return marker
    }

    override fun zoomOnCluster(mapView: MapView?, cluster: StaticCluster?) {
        var bb = cluster!!.boundingBox
        if (bb.latNorth == bb.latSouth && bb.lonEast == bb.lonWest) {
            mapView!!.setExpectedCenter(bb.centerWithDateLine)
        } else {
            bb = bb.increaseByScale(13f)
            mapView!!.zoomToBoundingBox(bb, true)
        }
    }

}