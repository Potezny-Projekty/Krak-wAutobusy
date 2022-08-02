package com.example.krakowautobusy.ui.map.vehicledata

import android.util.Log
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.FavouriteLineData
import com.example.krakowautobusy.ui.map.Drawables
import org.osmdroid.views.MapView

class ActualPositionFavouriteVehicle(drawables: Drawables) : ActualPositionVehicles(drawables) {


   override fun showAllVehicle(map: MapView, allVehicles: AllVehicles) {
      val listOfAllVehicle = allVehicles.vehicles
      val favouriteVehicles = Api.getApi().getAllFavouriteLine()
      listOfAllVehicle
         .filter { !it.isDeleted }
         .forEach {
            if (markers.containsKey(it.id)) {
               val drawVehicleMarker = markers[it.id]!!
               updateMarkerPosition(drawVehicleMarker, it, map)
            } else {
               val lineNumber = it.name.split(" ")[0].toInt()
               favouriteVehicles.forEach { favouriteLineData ->
                  if (favouriteLineData.numberLine == lineNumber) {
                     markers[it.id] = drawMarkerVehiclesOnMap(it, map)
                     return
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