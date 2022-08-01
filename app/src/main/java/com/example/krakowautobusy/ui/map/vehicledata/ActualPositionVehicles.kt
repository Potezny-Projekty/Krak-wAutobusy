package com.example.krakowautobusy.ui.map.vehicledata

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.SequenceVehicleStopData
import com.example.krakowautobusy.ui.map.Drawables
import com.google.gson.JsonObject
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import retrofit2.Response
import kotlin.collections.set


open class ActualPositionVehicles(var drawables: Drawables) {
    var lastUpdateBus: Long = 0
    var lastUpdateTram: Long = 0
    private var markers = mutableMapOf<String, VehicleMarker>()
    protected var trackedRoute : Polyline
    protected var traveledRoute : Polyline
    private val fullAngle = 360F
    private var enabled = true
    protected var trackingVehicle: Marker? = null
    protected lateinit var iconVehicleBeforeTracking : Drawable
    val NO_ELEMENT=0

    init {
        trackedRoute = createTrackedPolyline()
        traveledRoute = createTraveledPolyline()
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    open fun showAllVehicle(map: MapView, allVehicles: AllVehicles) {
        val listOfAllVehicle = allVehicles.vehicles
        listOfAllVehicle
            .filter { !it.isDeleted }
            .forEach {
                if (markers.containsKey(it.id)) {
                    val drawVehicleMarker = markers[it.id]!!
                    updateMarkerPosition(drawVehicleMarker, it, map)
                } else {
                    markers[it.id] = drawMarkerVehiclesOnMap(it, map)
            }
        }
        Log.i("POZYCJA", "TO Nie sa ulubione ULUBIONE")
    }

    fun showVehiclesAboutNumberLine(map:MapView, allVehicles: AllVehicles,numberLine:String){
        val listOfAllVehicle = allVehicles.vehicles
        listOfAllVehicle
            .filter { !it.isDeleted && it.name.startsWith(numberLine) }
            .forEach {
                if (markers.containsKey(it.id)) {
                    val drawVehicleMarker = markers[it.id]!!
                    updateMarkerPosition(drawVehicleMarker, it, map)
                } else {
                    drawMarkerVehiclesOnMap(it, map)
                }
            }
    }

    protected fun updateMarkerPosition(marker : VehicleMarker, vehicle: Vehicle, map : MapView) {
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
            Log.i("POSITION", "${lastPosition == marker.position} | ${lastPosition} = ${marker.position}")
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


    fun drawAllVehiclesStopLineOnMap(poz:ArrayList<SequenceVehicleStopData>,map:MapView){

        for(x in poz){
          val xx=  createMarker(map,x.nameVehicleStop)
            val locationPoint =
                ConvertUnits.convertToGeoPoint(x.longitude, x.latitude)

            Log.e("details","Rysuje"+x.nameVehicleStop+" "+locationPoint.latitude+" "+locationPoint.longitude)

            xx.position = locationPoint

            map.overlays.add(xx)

            map.invalidate()


        }
    }

    fun createMarker(map:MapView,namevehicleStop:String):Marker{
        val marker = Marker(map)
        //val ut=Utilities(getCo)

        marker.icon= drawables.vehicleStopIcon
        marker.id="xD"
        marker.title=namevehicleStop
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        return marker
    }

    protected fun fillMarkerData(marker: VehicleMarker, icon: Drawable, typeVehicle:String, title:String){
        val lineNumber = title.split(" ")[0]
        marker.icon= drawNumberOnIcon(icon, lineNumber)
        marker.id=typeVehicle
        marker.title=title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    }


    protected fun drawMarkerVehiclesOnMap(vehicle: Vehicle, map : MapView) : VehicleMarker {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = VehicleMarker(map, vehicle)
        val markerToast = MarkerToast(map)
        markerToast.view.setOnClickListener {
            val mapFragment = map.findFragment<Fragment>()
            mapFragment.setFragmentResult("details", bundleOf(Pair("vehicle", vehicle.name), Pair("tripId", vehicle.tripId.toString()), Pair("vehicleId", vehicle.id.toString())))
            map.findNavController().navigate(R.id.action_navigation_map_to_detailsFragment)

        }
        marker.setInfoWindowAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.infoWindow = markerToast
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()

        if (vehicle.category == VehicleType.BUS.type) {
            fillMarkerData(marker,drawables.busIconDrawable
                , VehicleType.BUS.type,vehicle.name)
        } else {
            fillMarkerData(marker,drawables.tramIconDrawable,
                VehicleType.TRAM.type,vehicle.name)
        }

        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            markerTracing.showInfoWindow()
            drawPathVehicle(vehicle.id, vehicle.category, mapView, marker)
        }

        return marker
    }


    public fun colorOnMapActualTimeTableVehicle(vehicleId:String,map:MapView){
        val marker=markers[vehicleId]
        marker?.showInfoWindow()
        if (marker != null) {
            drawPathVehicle(vehicleId, "TRAM", map, marker)
        }

    }



    protected fun analisePathVehicleResponse(response: Response<JsonObject>, map: MapView, marker: VehicleMarker){
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

    protected fun drawPathVehicle(idVehicle: String, type: String, map: MapView, marker: VehicleMarker) : Boolean{

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

    protected fun drawPathVehicleOnMap(map: MapView, marker: VehicleMarker,
                                pathPoints : ArrayList<GeoPoint>
    ) {
        removeTrackedVehicle()
        trackingVehicle = marker
        val lineNumber = marker.title.split(" ")[0]
        iconVehicleBeforeTracking = marker.icon
        if (marker.id == VehicleType.BUS.type) {
            marker.id = VehicleType.BUS_FOCUSED.type
            marker.icon = drawNumberOnIcon(drawables.busIconTrackingDrawable, lineNumber)
        } else {
            marker.id = VehicleType.TRAM_FOCUSED.type
            marker.icon = drawNumberOnIcon(drawables.tramIconTrackingDrawable, lineNumber)
        }

        val firstElement = 0
        var isRedLine = true
        var lastAddedPoint = pathPoints[firstElement]
        pathPoints.forEach {
            if (isRedLine) {
                val distance = lastAddedPoint.distanceToAsDouble(it)
                val distanceMareker = marker.position.distanceToAsDouble(lastAddedPoint)
                if (distance > distanceMareker) {
                    isRedLine = false
                    traveledRoute.addPoint(marker.position)
                    trackedRoute.addPoint(marker.position)
                } else {
                    traveledRoute.addPoint(it)
                }
                lastAddedPoint = it
            } else {
                trackedRoute.addPoint(it)
            }
        }
        map.invalidate()
    }

    protected fun createTrackedPolyline() : Polyline {
        val routeColor = "#39DD00"
        val width = 10.0f
        val trackedRoute = Polyline()
        trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        trackedRoute.outlinePaint.color = Color.parseColor(routeColor)
        trackedRoute.outlinePaint.strokeWidth = width
        trackedRoute.isGeodesic = true
        return trackedRoute
    }

    protected fun createTraveledPolyline() : Polyline {
        val colorTraveledRoute = "#FF0000"
        val width = 10.0f
        val traveledRoute = Polyline()
        traveledRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        traveledRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        traveledRoute.outlinePaint.color = Color.parseColor(colorTraveledRoute)
        traveledRoute.outlinePaint.strokeWidth = width
        traveledRoute.isGeodesic = true
        return traveledRoute
    }

    //to niżej dodać
    fun checkIsResponseExist(){

    }

    fun showNumberLine(map:MapView,numberLine: String){
        Api.getApi().getBusPosition(lastUpdateBus,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null) {
                    val allBus = response.body()!!
                    lastUpdateBus = allBus.lastUpdate
                    showVehiclesAboutNumberLine(map, response.body()!!,numberLine)
                    Log.i("ERRORR2", allBus.toString())
                }
            }
        )


        Api.getApi().getTramPosition(lastUpdateTram, fun(response: Response<AllVehicles>)  {
            if (response.isSuccessful) {
                val allTram = response.body()!!
                lastUpdateTram = allTram.lastUpdate
                showVehiclesAboutNumberLine(map, allTram,numberLine)
            }
        })
    }

    protected fun drawNumberOnIcon(icon : Drawable, number : String) : Drawable {
        val textSize = 18f
        val copyIcon = icon.mutate()
        val paint = Paint()
        val factoryMoveHeightText = 1.8
        val factoryMoveWidthText = 3
        val startPositionXText =  ((copyIcon.intrinsicHeight / factoryMoveHeightText) * -1).toFloat()
        val startPositionYText = ((copyIcon.intrinsicWidth / factoryMoveWidthText)).toFloat()
        val rotateCanvasToVerticle = -90f
        paint.color = Color.BLACK
        paint.textSize = textSize
        paint.textAlign = Paint.Align.CENTER
        val bitmap = Bitmap.createBitmap(
            copyIcon.intrinsicWidth,
            copyIcon.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        copyIcon.draw(canvas)
        canvas.rotate(rotateCanvasToVerticle)
        canvas.drawText(number, startPositionXText,
            startPositionYText, paint)
        copyIcon.setBounds(0, 0, canvas.width, canvas.height)
        return bitmap.toDrawable(Resources.getSystem())

    }

    fun lodaIconIntoMap() {
        val lineNumber = "10"
        drawNumberOnIcon(drawables.busIconTrackingDrawable, lineNumber)
        drawNumberOnIcon(drawables.tramIconTrackingDrawable, lineNumber)
    }

    fun changeVehicleOnFavoriteVehicles(map: MapView, isFavourite : Boolean) {
        if (isFavourite) {
            map.overlays.clear()
            markers.clear()
        } else {

        }
    }

    fun addPolylineIntoMap(map: MapView) {
        map.overlays.add(trackedRoute)
        map.overlays.add(traveledRoute)
    }

    open fun hiddenMarkers(map: MapView) {
        map.overlays.removeAll(markers.values)
        map.invalidate()
    }

    open fun showMarkers(map: MapView) {
        map.overlays.addAll(markers.values)
        map.invalidate()
    }

    fun removeTrackedVehicle() {
        if (trackingVehicle != null) {
            if (trackingVehicle!!.id == VehicleType.BUS_FOCUSED.type) {
                trackingVehicle!!.icon = iconVehicleBeforeTracking
                trackingVehicle!!.id = VehicleType.BUS.type
            } else {
                trackingVehicle!!.icon = iconVehicleBeforeTracking
                trackingVehicle!!.id = VehicleType.TRAM.type
            }
            trackingVehicle!!.infoWindow.close()
            trackedRoute.actualPoints.clear()
            traveledRoute.actualPoints.clear()
        }
    }

}