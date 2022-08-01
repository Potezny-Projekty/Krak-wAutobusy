package com.example.krakowautobusy.ui.map.vehicledata

import android.util.Log
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.FavouriteLineData
import com.example.krakowautobusy.ui.map.Drawables
import org.osmdroid.views.MapView

class ActualPositionFavouriteVehicle(drawables: Drawables) : ActualPositionVehicles(drawables) {
   private val markers = mutableMapOf<MarkerId, VehicleMarker>()


   override fun showAllVehicle(map: MapView, allVehicles: AllVehicles) {
      val listOfAllVehicle = allVehicles.vehicles
      val favouriteVehicles = Api.getApi().getAllFavouriteLine()
      listOfAllVehicle
         .filter { !it.isDeleted }
         .forEach {
            val markerId = MarkerId(it.name, it.id)
            if (markers.containsKey(markerId)) {
               val drawVehicleMarker = markers[markerId]!!
               updateMarkerPosition(drawVehicleMarker, it, map)
            } else {
               val lineNumber = it.name.split(" ")[0].toInt()
               favouriteVehicles.forEach { favouriteLineData ->
                  if (favouriteLineData.numberLine == lineNumber) {
                     markers[markerId] = drawMarkerVehiclesOnMap(it, map)
                  }
               }
            }
         }
   }


   override fun hiddenMarkers(map: MapView) {
      map.overlays.removeAll(markers.values)
      map.invalidate()
   }

   override fun showMarkers(map: MapView) {
      map.overlays.addAll(markers.values)
      map.invalidate()
   }

}