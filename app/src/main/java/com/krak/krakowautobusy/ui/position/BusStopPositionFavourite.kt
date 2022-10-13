package com.krak.krakowautobusy.ui.position

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.Toast
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay

class BusStopPositionFavourite(busStopIconDrawable: Drawable) :
    BusStopPosition(busStopIconDrawable) {
        private val busStops : FolderOverlay = FolderOverlay()

    override fun createAllBusStopsMarkers(map: MapView) {
        hiddenAllBusStops(map)
        busStops.items.clear()
        val busStopData = Api.getApi().getAllFavouriteVehicleStop()

        if (busStopData.isEmpty()) {
            val informationAboutFavourite =
                Resources.getSystem().getString(R.string.infoNoFavouriteVehicleStop)
            Toast.makeText(map.context,
                informationAboutFavourite, Toast.LENGTH_LONG).show()

        } else {
            busStopData.forEach {
                busStops.add(createBusStopMarker(map, it))
            }
        }
    }

    override fun showAllBusStops(map: MapView) {
        createAllBusStopsMarkers(map)
        map.overlays.addAll(busStops.items)
        map.invalidate()
    }

    override fun hiddenAllBusStops(map: MapView) {
        busStops.closeAllInfoWindows()
        map.overlays.removeAll(busStops.items)
        map.invalidate()
    }


}