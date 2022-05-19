package com.example.krakowautobusy.ui.map.vehicledata

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.util.Log
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.drawable.toIcon
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.ui.map.Drawables
import com.google.gson.JsonObject
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import retrofit2.Response


class ActualPositionVehicles(var drawables: Drawables) {
    private var lastUpdateBus: Long = 0
    private var lastUpdateTram: Long = 0
    private var markers = mutableMapOf<String, Marker>()
    private var trackedRoute = Polyline()
    private var traveledRoute = Polyline()

    private val fullAngle = 360F
    private var enabled = true
    private var trackingVehicle: Marker? = null
    val NO_ELEMENT=0


    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    private fun showAllVehicle(map: MapView, allVehicles: AllVehicles) {
        val listOfAllVehicle = allVehicles.vehicles
        listOfAllVehicle
            .filter { !it.isDeleted }
            .forEach {
                if (markers.containsKey(it.id)) {
                    val drawVehicleMarker = markers[it.id]!!
                    updateMarkerPosition(drawVehicleMarker, it, map)
                } else {
                    drawMarkerVehiclesOnMap(it, map)
            }
        }
    }

    private fun updateMarkerPosition(marker : Marker, vehicle: Vehicle, map : MapView) {
        if (vehicle.path.size > NO_ELEMENT) {
            val lastPosition = ConvertUnits.convertToGeoPoint(vehicle.path[vehicle.path.size - 1].y2,
                vehicle.path[vehicle.path.size - 1].x2 )
            if (lastPosition != marker.position) {
                MarkerAnimation.animateMarkerToHC(
                    map,
                    marker,
                    vehicle.path,
                    GeoPointInterpolator.Linear(),
                    traveledRoute
                )
            }
        } else {
            MarkerAnimation.animateMarkerToHCLinear(
                map, marker,
                ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude),
                GeoPointInterpolator.Linear(),
                traveledRoute
            )
            marker.rotation = fullAngle - vehicle.heading
        }
    }


    private fun fillMarkerData(marker: Marker, icon: Drawable, typeVehicle:String, title:String){
        val lineNumber = title.split(" ")[0]
        marker.icon= drawNumberOnIcon(icon, lineNumber)
        marker.id=typeVehicle
        marker.title=title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    }


    private fun drawMarkerVehiclesOnMap(vehicle: Vehicle, map : MapView) {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = Marker(map)


        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()

        if (vehicle.category == VehicleType.BUS.type) {
            fillMarkerData(marker,drawables.busIconDrawable, VehicleType.BUS.type,vehicle.name)
        } else {

            fillMarkerData(marker,drawables.tramIconDrawable,VehicleType.TRAM.type,vehicle.name)
        }

        markers[vehicle.id] = marker
        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            markerTracing.showInfoWindow()
            drawPathVehicle(vehicle.id, vehicle.category, mapView, marker)
        }
    }



    private fun analisePathVehicleResponse(response: Response<JsonObject>, map: MapView, marker: Marker){
        val FIRST_RESPONSE_ELEM=0
        val PATH="paths"
        val WAY_POINT="wayPoints"
        val LATITUDE="lat"
        val LONGITUDE="lon"


        Log.i("ERRORR2", response.raw().toString())
        val geoPoints = ArrayList<GeoPoint>()
        val jsonObjectValue = response.body()!!
        jsonObjectValue.getAsJsonArray(PATH)[FIRST_RESPONSE_ELEM]
            .asJsonObject.getAsJsonArray(WAY_POINT)
            .forEach {
                geoPoints.add(
                    ConvertUnits.convertToGeoPoint(
                        it.asJsonObject[LATITUDE].asLong,
                        it.asJsonObject[LONGITUDE].asLong
                    )
                )
            }
        drawPathVehicleOnMap(map, marker, geoPoints)
    }

    private fun drawPathVehicle(idVehicle: String, type: String, map: MapView, marker: Marker) : Boolean{

        if (type == VehicleType.TRAM.type) {
            Api.getApi().getTramPath(idVehicle,fun( response: Response<JsonObject>) {
               analisePathVehicleResponse(response,map,marker)
            })
        } else {
            Api.getApi().getBusPath(idVehicle,fun( response: Response<JsonObject>) {
                analisePathVehicleResponse(response,map,marker)
            })

        }

        return true
    }

    private fun drawPathVehicleOnMap(map: MapView, marker: Marker,
                                pathPoints : ArrayList<GeoPoint>
    ) {
        if (trackingVehicle != null) {

            if (trackingVehicle!!.id == VehicleType.BUS_FOCUSED.type) {
                trackingVehicle!!.icon = drawables.resizedBusIcon
                trackingVehicle!!.id = VehicleType.BUS.type
            } else {
                trackingVehicle!!.icon = drawables.resizedTramIcon
                trackingVehicle!!.id = VehicleType.TRAM.type
            }
        }
        trackingVehicle = marker
        if (marker.id == VehicleType.BUS.type) {
            marker.id = VehicleType.BUS_FOCUSED.type
            marker.icon = drawables.resizedBusIconTracking
        } else {
            marker.id = VehicleType.TRAM_FOCUSED.type
            marker.icon = drawables.resizedTramIconTracking
        }
        trackedRoute.actualPoints.clear()
        traveledRoute.actualPoints.clear()
        val firstElement = 0;
        pathPoints.forEach {
            if (pathPoints[firstElement].distanceToAsDouble(it)
                < pathPoints[firstElement].distanceToAsDouble(marker.position)) {
                traveledRoute.addPoint(it)
            } else {
                trackedRoute.addPoint(it)
            }
        }
        map.invalidate()
    }

    fun createPolyline(tracked: Polyline, traveled : Polyline, width: Float, color: String) {
        val colorTraveledRoute = "#FF0000";

        trackedRoute = tracked
        trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        trackedRoute.outlinePaint.color = Color.parseColor(color)
        trackedRoute.outlinePaint.strokeWidth = width
        trackedRoute.isGeodesic = true

        traveledRoute = traveled
        traveledRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        traveledRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        traveledRoute.outlinePaint.color = Color.parseColor(colorTraveledRoute)
        traveledRoute.outlinePaint.strokeWidth = width
        traveledRoute.isGeodesic = true
    }

    //to niżej dodać
    fun checkIsResponseExist(){

    }

    fun getActualPosition(map : MapView){
      //  combine: (responseFun: Response<AllVehicles>) -> Unit
      Api.getApi().getBusPosition(lastUpdateBus,
          fun(response: Response<AllVehicles>) {
              if (response.isSuccessful && response.body() != null) {
                  val allBus = response.body()!!
                  lastUpdateBus = allBus.lastUpdate
                  showAllVehicle(map, response.body()!!)
                  Log.i("ERRORR2", allBus.toString())
              }
          }
        )


      Api.getApi().getTramPosition(lastUpdateTram, fun(response: Response<AllVehicles>)  {
          if (response.isSuccessful) {
              val allTram = response.body()!!
              lastUpdateTram = allTram.lastUpdate
              showAllVehicle(map, allTram)
          }
      })

    }

    private fun drawNumberOnIcon(icon : Drawable, number : String) : Drawable {
        val textSize = 20f;
        val copyIcon = icon.mutate()
        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = textSize
        val bitmap = Bitmap.createBitmap(
            copyIcon.intrinsicWidth,
            copyIcon.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        copyIcon.setBounds(0, 0, canvas.width, canvas.height)
        canvas.drawText(number, 20f, 20f, paint)
        copyIcon.draw(canvas)
        return bitmap.toDrawable(Resources.getSystem())
    }
}