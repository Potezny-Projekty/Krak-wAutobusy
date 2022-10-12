package com.krak.krakowautobusy.ui.map.vehicledata
import android.content.res.Resources
import android.widget.Toast
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.ui.map.Drawables
import org.osmdroid.views.MapView

class ActualPositionFavouriteVehicle(drawables: Drawables) : ActualPositionVehicles(drawables) {


   override fun showFavouriteVehicles(map: MapView, allVehicles: AllVehicles) {
      val space=" "
      try {
         val listOfAllVehicle = allVehicles.vehicles
         val favouriteVehicles = Api.getApi().getAllFavouriteLine()
         if (favouriteVehicles.isEmpty()) {
            val informationAboutFavourite =Resources.getSystem().getString(R.string.infoNoFavouriteVehicles)

            Toast.makeText(map.context, informationAboutFavourite, Toast.LENGTH_LONG).show()
         } else {
            listOfAllVehicle
               .filter { !it.isDeleted }
               .forEach {
                  if (markers.containsKey(it.id)) {
                     val drawVehicleMarker = markers[it.id]!!
                     updateMarkerPosition(drawVehicleMarker, it, map)
                  } else {
                     val firstElement = 0
                     val lineNumber = it.name.split(space)[firstElement].toIntOrNull()
                     if (lineNumber != null) {
                        favouriteVehicles.forEach { favouriteLineData ->
                           if (favouriteLineData.numberLine == lineNumber) {
                              markers[it.id] = drawMarkerVehiclesOnMap(it, map)
                              return
                           }
                        }
                     }
                  }
               }
         }
      }catch (exp:Exception){

      }
   }


   override fun hiddenMarkers(map: MapView) {
      map.overlays.removeAll(markers.values)
      markers.clear()
      map.invalidate()
   }

   override fun showMarkers(map: MapView) {
      map.overlays.addAll(markers.values)
      map.invalidate()
   }

}