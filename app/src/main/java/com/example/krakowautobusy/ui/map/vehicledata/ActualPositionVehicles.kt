package com.example.krakowautobusy.ui.map.vehicledata

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MotionEvent
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.krakowautobusy.BundleChoiceVehicle
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
    protected var markers = mutableMapOf<String, VehicleMarker>()
    protected var trackedRoute : Polyline
    protected var traveledRoute : Polyline
    private val fullAngle = 360F
    private var enabled = true
    protected var trackingVehicle: VehicleMarker? = null
    protected lateinit var iconVehicleBeforeTracking : Drawable
    val tramDrawable : VehicleDrawables
    val busDrawable : VehicleDrawables
    val NO_ELEMENT=0


    companion object{
        public val actualVehicleIdClick = MutableLiveData<String>().apply {
            value = ""
        }
    }




    init {
        trackedRoute = createTrackedPolyline()
        traveledRoute = createTraveledPolyline()
        tramDrawable = VehicleDrawables(  drawables.tramIconDrawable,
            drawables.tramIconMirrorDrawable,
            drawables.tramIconTrackingDrawable,
            drawables.tramIconTrackingMirrorDrawable)
        busDrawable = VehicleDrawables(  drawables.busIconDrawable,
            drawables.busIconMirrorDrawable,
            drawables.busIconTrackingDrawable,
            drawables.busIconTrackingMirrorDrawable)
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
            .filter { !it.isDeleted && it.name.startsWith(numberLine) && it.name.replace("[^0-9]".toRegex(), "").length==numberLine.length }
            .forEach {
                if (markers.containsKey(it.id)) {

                    markers[it.id]!!.setOnMarkerClickListener { markerTracing, mapView ->
                       // drawMarkerVehiclesOnMap(it,mapView)
                        colorOnMapActualTimeTableVehicle(it.id,mapView)
                        actualVehicleIdClick.value=it.id


                        true
                    }


                    val drawVehicleMarker = markers[it.id]!!
                    updateMarkerPosition(drawVehicleMarker, it, map)
                } else {
                    markers[it.id] = drawMarkerVehiclesOnMapAboutNumberLine(it, map)
                }
            }
    }

    protected fun updateMarkerPosition(marker : VehicleMarker, vehicle: Vehicle, map : MapView) {
        if (vehicle.path.size > NO_ELEMENT) {
            val lastPosition = ConvertUnits.convertToGeoPoint(vehicle.path[vehicle.path.size - 1].y2,
                vehicle.path[vehicle.path.size - 1].x2 )
            Log.i("VEHICLEPOSITION", "last position :${lastPosition}, marker Position ${marker.position}")
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
           /* val points = ArrayList<GeoPoint>()
            points.add(marker.position)
            points.add(ConvertUnits
                .convertToGeoPoint(vehicle.latitude,
                    vehicle.longitude))
            val road = roadManager.getRoad(points)
            val path = road.mRouteHigh
            MarkerAnimation.animateMarkerToHC(
                map,
                marker,
                path,
                GeoPointInterpolator.Linear(),
                traveledRoute
            )
            marker.rotation = fullAngle - vehicle.heading*/
        }
    }


    fun drawAllVehiclesStopLineOnMap(poz:ArrayList<SequenceVehicleStopData>,map:MapView){
        val  busStopMarkers = BusStopMarkerClusterDetails(map.context)
        val busStopMarkerCollectionRadiusForClustering = 60
        busStopMarkers.setRadius(busStopMarkerCollectionRadiusForClustering)
        for(x in poz){
          val xx=  createMarker(map,x.nameVehicleStop)
            val locationPoint =
                ConvertUnits.convertToGeoPoint(x.longitude, x.latitude)

            Log.e("details","Rysuje"+x.nameVehicleStop+" "+locationPoint.latitude+" "+locationPoint.longitude)

            xx.position = locationPoint
            busStopMarkers.add(xx)
        }
        map.overlays.add(busStopMarkers)
        map.invalidate()
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

    protected fun fillMarkerData(marker: VehicleMarker, icons: VehicleDrawables,
                                 typeVehicle:String, title:String){
        val lineNumber = title.split(" ")[0]
        val halfAngle = 180
        marker.setVehicleIcon(icons.vehicleIcon, lineNumber)
        marker.setVehicleIconMirror(icons.vehicleIconMirror, lineNumber)
        marker.setVehicleTrackedIcon(icons.vehicleTrackedIcon, lineNumber)
        marker.setVehicleTrackedIconMirror(icons.vehicleTrackedIconMirror, lineNumber)

        if (marker.rotation < halfAngle) {
            marker.icon = marker.vehicleIconMirror
        } else {
            marker.icon = marker.vehicleIcon
        }

        marker.id=typeVehicle
        marker.title=title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    }


    @SuppressLint("ClickableViewAccessibility")
    protected fun drawMarkerVehiclesOnMap(vehicle: Vehicle, map : MapView) : VehicleMarker {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = VehicleMarker(map, vehicle)
        val markerToast = MarkerToast(map)
        markerToast.view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val mapFragment = map.findFragment<Fragment>()
                val bundle = bundleOf(
                    BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                            vehicle.name.toString().split(' ')[0].trim().toInt(),
                    BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                            to "",
                    BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                            vehicle.name.toString().substringAfter(" ").toString() ,"tripId" to vehicle.tripId


                )

                map.findNavController().navigate(R.id.action_navigation_map_to_detailsFragment,bundle);
            }
            true
        }


        marker.setInfoWindowAnchor(Marker.ANCHOR_TOP,  Marker.ANCHOR_CENTER)
        marker.infoWindow = markerToast
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()

        if (vehicle.category == VehicleType.BUS.type) {
            fillMarkerData(marker,busDrawable
                , VehicleType.BUS.type,vehicle.name)
        } else {
            fillMarkerData(marker,tramDrawable,
                VehicleType.TRAM.type,vehicle.name)
        }

        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            marker.adjustPositionInfowWindowRelativeToRotationIcon()
            markerTracing.showInfoWindow()
            drawPathVehicle(vehicle.id, vehicle.category, mapView, marker)
        }

        return marker
    }

    protected fun drawMarkerVehiclesOnMapAboutNumberLine(vehicle: Vehicle, map : MapView) : VehicleMarker {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = VehicleMarker(map, vehicle)
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()
        if (vehicle.category == VehicleType.BUS.type) {
            fillMarkerData(marker,busDrawable
                , VehicleType.BUS.type,vehicle.name)
        } else {
            fillMarkerData(marker,tramDrawable,
                VehicleType.TRAM.type,vehicle.name)
        }

        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            true
        }

        return marker
    }

  var actualShow:String=""
    var x=false
    public fun colorOnMapActualTimeTableVehicle(vehicleId:String,map:MapView){
       if(actualShow!=vehicleId.toString()) {
            val marker = markers[vehicleId]

         //   for (x in markers) {
          //      Log.e("qweqwe2", x.key + " ")
       //     }

           if(!x){
               x=true
               addPolylineIntoMap(map)
           }

         //   Log.e("qweqwe", "Jestem")
         //   Log.e("qweqwe", vehicleId.toString())
            if (marker != null) {
                drawPathVehicle(vehicleId, "tram", map, marker)
                drawPathVehicle(vehicleId, "BUS", map, marker)




            } //else {
            //    Log.e("qweqwe", "Jestem NULL")
          //  }

            actualShow=vehicleId
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
        val jsonObjectValue = response.body()
        jsonObjectValue?.getAsJsonArray(PATH)?.get(FIRST_RESPONSE_ELEM)
            ?.asJsonObject?.getAsJsonArray(WAY_POINT)
            ?.forEach {
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

        if(pathPoints.size>0 && marker.icon!=null){
        removeTrackedVehicle()
        trackingVehicle = marker
        iconVehicleBeforeTracking = marker.icon
        marker.switchedBetweenTrackingAndStandardIcon()
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
    }}

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

    protected fun drawNumberOnIcon(icon : Drawable, number : String, rotation : Float) : Drawable {
        val textSize = 24f
        val copyIcon = icon.mutate()
        val paint = Paint()
        val factoryMoveHeightText = 2
        val factoryMoveWidthText = 3.3
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
        canvas.scale(
            -1f, 1f, canvas.width / 2f,
            canvas.height / 2f
        )
        copyIcon.draw(canvas)
        canvas.rotate(rotateCanvasToVerticle)
        canvas.drawText(number, startPositionXText,
            startPositionYText, paint)
        Log.i("ROTATION", rotation.toString())
        copyIcon.setBounds(0, 0, canvas.width, canvas.height)
        return bitmap.toDrawable(Resources.getSystem())

    }

    fun lodaIconIntoMap() {
        val lineNumber = "10"
        //Drawables Bus
        drawNumberOnIcon(drawables.busIconTrackingDrawable, lineNumber, 0f)
        drawNumberOnIcon(drawables.busIconTrackingMirrorDrawable, lineNumber, 0f)
        drawNumberOnIcon(drawables.busIconMirrorDrawable, lineNumber, 0f)
        drawNumberOnIcon(drawables.busIconDrawable, lineNumber, 0f)

        //Drawables Tram
        drawNumberOnIcon(drawables.tramIconTrackingDrawable, lineNumber, 0f)
        drawNumberOnIcon(drawables.tramIconTrackingMirrorDrawable, lineNumber, 0f)
        drawNumberOnIcon(drawables.tramIconMirrorDrawable, lineNumber, 0f)
        drawNumberOnIcon(drawables.tramIconDrawable, lineNumber, 0f)
    }


    fun addPolylineIntoMap(map: MapView) {
        val firstElement = 0;
        val secondElement = 1;
        map.overlays.add(firstElement, trackedRoute)
        map.overlays.add(secondElement ,traveledRoute)
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
            trackingVehicle!!.switchedBetweenTrackingAndStandardIcon()

            trackingVehicle?.infoWindow?.close()
            trackedRoute.actualPoints.clear()
            traveledRoute.actualPoints.clear()
        }
    }
}