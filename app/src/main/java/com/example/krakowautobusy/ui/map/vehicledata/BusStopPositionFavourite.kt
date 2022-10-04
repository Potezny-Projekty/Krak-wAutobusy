package com.example.krakowautobusy.ui.map.vehicledata

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import com.example.krakowautobusy.api.Api
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Overlay

class BusStopPositionFavourite(busStopIconDrawable: Drawable,view: View) :
    BusStopPosition(busStopIconDrawable) {
        private val busStops : FolderOverlay = FolderOverlay()

    override fun createAllBusStopsMarkers(map: MapView) {
        hiddenAllBusStops(map)
        busStops.items.clear()
        val busStopData = Api.getApi().getAllFavouriteVehicleStop()

        if (busStopData.isEmpty()) {
            val informationAboutFavourite =
                "Musisz dodać ulubione przystanki, aby je wyświetlić"
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