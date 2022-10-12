package com.krak.krakowautobusy.ui.map.vehicledata

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.krak.krakowautobusy.BundleChoiceVehicle
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.SequenceVehicleStopData
import com.krak.krakowautobusy.ui.map.Drawables
import com.google.gson.JsonObject
import com.krak.krakowautobusy.database.VehicleStopData
import com.krak.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
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
    private var trackedRoute: Polyline
    private var traveledRoute: Polyline
    private val fullAngle = 360F
    private var enabled = true
    private var trackingVehicle: VehicleMarker? = null
    private lateinit var iconVehicleBeforeTracking: Drawable
    private val tramDrawable: VehicleDrawables
    private val busDrawable: VehicleDrawables
    private val noElem = 0


    companion object {
        val actualVehicleIdClick = MutableLiveData<String>().apply {
            value = ""
        }
    }


    init {
        trackedRoute = createTrackedPolyline()
        traveledRoute = createTraveledPolyline()
        tramDrawable = VehicleDrawables(
            drawables.tramIconDrawable,
            drawables.tramIconMirrorDrawable,
            drawables.tramIconTrackingDrawable,
            drawables.tramIconTrackingMirrorDrawable
        )
        busDrawable = VehicleDrawables(
            drawables.busIconDrawable,
            drawables.busIconMirrorDrawable,
            drawables.busIconTrackingDrawable,
            drawables.busIconTrackingMirrorDrawable
        )
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    open fun showFavouriteVehicles(map: MapView, allVehicles: AllVehicles) {

        try {

            val listOfAllVehicle = allVehicles.vehicles
            listOfAllVehicle
                .filter { !it.isDeleted }
                .forEach {
                    if (markers.containsKey(it.id)) {
                        val drawVehicleMarker = markers[it.id]!!
                        drawVehicleMarker.drawPathVehicle(this, map)
                        updateMarkerPosition(drawVehicleMarker, it, map)
                    } else {
                        markers[it.id] = drawMarkerVehiclesOnMap(it, map)
                    }
                }


        } catch (exp: Exception) {

        }
    }


    private fun showVehiclesAboutNumberLine(map: MapView, allVehicles: AllVehicles, numberLine: String) {
        val regexNumber=  "[^0-9]".toRegex()
        val emptyString=""
        try {
            val listOfAllVehicle = allVehicles.vehicles
            listOfAllVehicle
                .filter {
                    !it.isDeleted && it.name.startsWith(numberLine) && it.name.replace(
                     regexNumber ,
                        emptyString
                    ).length == numberLine.length
                }
                .forEach {
                    if (markers.containsKey(it.id)) {

                        markers[it.id]!!.setOnMarkerClickListener { _, mapView ->
                            colorOnMapActualTimeTableVehicle(it.id, mapView)
                            actualVehicleIdClick.value = it.id
                            true
                        }


                        val drawVehicleMarker = markers[it.id]!!
                        drawVehicleMarker.drawPathVehicle(this, map)
                        updateMarkerPosition(drawVehicleMarker, it, map)
                    } else {
                        markers[it.id] = drawMarkerVehiclesOnMapAboutNumberLine(it, map)
                    }
                }

        } catch (exp: Exception) {

        }
    }

    protected fun updateMarkerPosition(marker: VehicleMarker, vehicle: Vehicle, map: MapView) {
        if (vehicle.path.size > noElem) {
            val lastPosition = ConvertUnits.convertToGeoPoint(
                vehicle.path[vehicle.path.size - 1].y2,
                vehicle.path[vehicle.path.size - 1].x2
            )

            if (lastPosition != marker.position) {
                MarkerAnimation.animateMarkerToHC(
                    map,
                    marker,
                    vehicle.path,
                    GeoPointInterpolator.Linear(),
                    traveledRoute
                )
            }
        }
    }



    @SuppressLint("ClickableViewAccessibility")
    fun addClickSnippetVehicleStopToMoveVehicleStopDetails(vehicleStopMarker: BusStopMarker){
        val defaultValue=""
        vehicleStopMarker.infoWindow.view.setOnTouchListener { viewSnippet, event ->


            viewSnippet as ViewGroup
            if (event.action == MotionEvent.ACTION_DOWN) {
                val nameVehicleStop=(((viewSnippet.getChildAt(1) as LinearLayout).getChildAt(0) as LinearLayout) .getChildAt(0) as TextView).text.toString()

                val bundle = bundleOf(
                    Bundle_Vehicle_Stop.ID_VEHICLE_STOP.nameBundle to
                            defaultValue,
                    Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                            nameVehicleStop,
                    Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                            defaultValue

                )


                Navigation.findNavController(viewSnippet)
                    .navigate(R.id.actionnavigatedetailesstop, bundle)
            }
            true
        }
    }

    fun drawAllVehiclesStopForLinesOnMap(vehicleStops: ArrayList<SequenceVehicleStopData>, map: MapView) {
        val busStopMarkers = BusStopMarkerClusterDetails(map.context)
        val busStopMarkerCollectionRadiusForClustering = 60
        val defaultValue=0L
        busStopMarkers.setRadius(busStopMarkerCollectionRadiusForClustering)
        for (vehStop in vehicleStops) {
            val marker = createMarker(map, vehStop.nameVehicleStop)
            val vehicleStopMarker=BusStopMarker(map, VehicleStopData(defaultValue,vehStop.nameVehicleStop,vehStop.longitude,vehStop.latitude,
                defaultValue.toInt(),com.krak.krakowautobusy.database.VehicleType.BUS,vehStop.idStopPoint,false))

            addClickSnippetVehicleStopToMoveVehicleStopDetails(vehicleStopMarker)

            val locationPoint =
                ConvertUnits.convertToGeoPoint(vehStop.longitude, vehStop.latitude)

            marker.position = locationPoint
            busStopMarkers.add(marker)
        }
        map.overlays.add(busStopMarkers)
        map.invalidate()
    }

    private fun createMarker(map: MapView, namevehicleStop: String): Marker {
        val marker = Marker(map)

        marker.icon = drawables.vehicleStopIcon
        marker.id = ".."
        marker.title = namevehicleStop
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        return marker
    }

    private fun fillMarkerData(
        marker: VehicleMarker, icons: VehicleDrawables,
        typeVehicle: String, title: String
    ) {
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

        marker.id = typeVehicle
        marker.title = title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun setClickSnippetVehicleMarkerToMoveToVehicleDetails(markerToast:MarkerToast, vehicle: Vehicle, map: MapView){
        val defaultValue=""
        val space=" "
        val bundleKeyTripId="tripId"
        markerToast.view.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val bundle = bundleOf(
                    BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                            vehicle.name.split(space)[0].trim().toInt(),
                    BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                            to defaultValue,
                    BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                            vehicle.name.substringAfter(space),
                    bundleKeyTripId to vehicle.tripId

                )

                map.findNavController()
                    .navigate(R.id.action_navigation_map_to_detailsFragment, bundle)
            }
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    protected fun drawMarkerVehiclesOnMap(vehicle: Vehicle, map: MapView): VehicleMarker {


        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = VehicleMarker(map, vehicle)
        val markerToast = MarkerToast(map)

        setClickSnippetVehicleMarkerToMoveToVehicleDetails(markerToast,vehicle,map)


        marker.setInfoWindowAnchor(Marker.ANCHOR_TOP, Marker.ANCHOR_CENTER)
        marker.infoWindow = markerToast
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()

        if (vehicle.category == VehicleType.BUS.type) {
            fillMarkerData(
                marker, busDrawable, VehicleType.BUS.type, vehicle.name
            )
        } else {
            fillMarkerData(
                marker, tramDrawable,
                VehicleType.TRAM.type, vehicle.name
            )
        }

        map.overlays.add(marker)
        marker.setOnMarkerClickListener { markerTracing, mapView ->
            marker.adjustPositionInfowWindowRelativeToRotationIcon()
            markerTracing.showInfoWindow()
            drawPathVehicle(vehicle.id, vehicle.category, mapView, marker)
        }
        return marker


    }

    private fun drawMarkerVehiclesOnMapAboutNumberLine(
        vehicle: Vehicle,
        map: MapView
    ): VehicleMarker {
        val locationPoint =
            ConvertUnits.convertToGeoPoint(vehicle.latitude, vehicle.longitude)
        val marker = VehicleMarker(map, vehicle)
        marker.position = locationPoint
        marker.rotation = fullAngle - vehicle.heading.toFloat()
        if (vehicle.category == VehicleType.BUS.type) {
            fillMarkerData(
                marker, busDrawable, VehicleType.BUS.type, vehicle.name
            )
        } else {
            fillMarkerData(
                marker, tramDrawable,
                VehicleType.TRAM.type, vehicle.name
            )
        }

        map.overlays.add(marker)


        return marker
    }

    private var actualShowVehicleID: String = ""
    private var isFirstRun = false
    fun colorOnMapActualTimeTableVehicle(vehicleId: String, map: MapView) {
        if (actualShowVehicleID != vehicleId) {
            val marker = markers[vehicleId]

            if (!isFirstRun) {
                isFirstRun = true
                addPolylineIntoMap(map)
            }

            if (marker != null) {
                drawPathVehicle(vehicleId, "tram", map, marker)
                drawPathVehicle(vehicleId, "BUS", map, marker)

            }
            actualShowVehicleID = vehicleId
        }

    }


    private fun analisePathVehicleResponse(
        response: Response<JsonObject>,
        map: MapView,
        marker: VehicleMarker
    ) {
        val firstResponseElem = 0
        val path = "paths"
        val wayPoint = "wayPoints"
        val latitude = "lat"
        val longitude = "lon"

        val geoPoints = ArrayList<GeoPoint>()
        val jsonObjectValue = response.body()
        jsonObjectValue?.getAsJsonArray(path)?.get(firstResponseElem)
            ?.asJsonObject?.getAsJsonArray(wayPoint)
            ?.forEach {
                geoPoints.add(
                    ConvertUnits.convertToGeoPoint(
                        it.asJsonObject[latitude].asLong,
                        it.asJsonObject[longitude].asLong
                    )
                )
            }
        marker.pathVehicle = geoPoints
        drawPathVehicleOnMap(map, marker, geoPoints)
    }

    private fun drawPathVehicle(
        idVehicle: String,
        type: String,
        map: MapView,
        marker: VehicleMarker
    ): Boolean {

        if (type == VehicleType.TRAM.type) {
            Api.getApi().getTramPath(idVehicle, fun(response: Response<JsonObject>) {
                analisePathVehicleResponse(response, map, marker)
            })
        } else {
            Api.getApi().getBusPath(idVehicle, fun(response: Response<JsonObject>) {
                analisePathVehicleResponse(response, map, marker)
            })

        }
        return true
    }

    fun drawPathVehicleOnMap(
        map: MapView, marker: VehicleMarker,
        pathPoints: ArrayList<GeoPoint>
    ) {

        if (pathPoints.size > 0 && marker.icon != null) {
            if (marker != trackingVehicle) {
                removeTrackedVehicle()
                trackingVehicle = marker
                iconVehicleBeforeTracking = marker.icon
                marker.switchedBetweenTrackingAndStandardIcon()
            } else {
                removeTrackedPathVehicle()
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
    }

    private fun createTrackedPolyline(): Polyline {
        val routeColor = Resources.getSystem().getColor(R.color.routeColor)
        val width = 10.0f
        val trackedRoute = Polyline()
        trackedRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        trackedRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        trackedRoute.outlinePaint.color = routeColor
        trackedRoute.outlinePaint.strokeWidth = width
        trackedRoute.isGeodesic = true
        return trackedRoute
    }

    private fun createTraveledPolyline(): Polyline {
        val colorTraveledRoute = Resources.getSystem().getColor(R.color.traveledColorRoute)
        val width = 10.0f
        val traveledRoute = Polyline()
        traveledRoute.outlinePaint.strokeCap = Paint.Cap.ROUND
        traveledRoute.outlinePaint.strokeJoin = Paint.Join.ROUND
        traveledRoute.outlinePaint.color = colorTraveledRoute
        traveledRoute.outlinePaint.strokeWidth = width
        traveledRoute.isGeodesic = true
        return traveledRoute
    }


    fun showNumberLine(map: MapView, numberLine: String) {
        Api.getApi().getBusPosition(lastUpdateBus,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null) {
                    val allBus = response.body()!!
                    lastUpdateBus = allBus.lastUpdate
                    showVehiclesAboutNumberLine(map, response.body()!!, numberLine)
                }
            }
        )


        Api.getApi().getTramPosition(lastUpdateTram, fun(response: Response<AllVehicles>) {
            if (response.isSuccessful) {
                val allTram = response.body()!!
                lastUpdateTram = allTram.lastUpdate
                showVehiclesAboutNumberLine(map, allTram, numberLine)
            }
        })
    }

    private fun drawNumberOnIcon(icon: Drawable, number: String): Drawable {

        val textSize = 15f * drawables.context.resources.displayMetrics.density

        val copyIcon = icon.mutate()
        val paint = Paint()
        val factoryMoveHeightText = 2
        val factoryMoveWidthText = 3.3
        val startPositionXText = ((copyIcon.intrinsicHeight / factoryMoveHeightText) * -1).toFloat()
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
        canvas.drawText(
            number, startPositionXText,
            startPositionYText, paint
        )

        copyIcon.setBounds(0, 0, canvas.width, canvas.height)
        return bitmap.toDrawable(Resources.getSystem())

    }

    fun lodaIconIntoMap() {
        val lineNumber = "10"
        //Drawables Bus
        drawNumberOnIcon(drawables.busIconTrackingDrawable, lineNumber)
        drawNumberOnIcon(drawables.busIconTrackingMirrorDrawable, lineNumber)
        drawNumberOnIcon(drawables.busIconMirrorDrawable, lineNumber)
        drawNumberOnIcon(drawables.busIconDrawable, lineNumber)

        //Drawables Tram
        drawNumberOnIcon(drawables.tramIconTrackingDrawable, lineNumber)
        drawNumberOnIcon(drawables.tramIconTrackingMirrorDrawable, lineNumber)
        drawNumberOnIcon(drawables.tramIconMirrorDrawable, lineNumber)
        drawNumberOnIcon(drawables.tramIconDrawable, lineNumber)
    }


    fun addPolylineIntoMap(map: MapView) {
        val firstElement = 0
        val secondElement = 1
        map.overlays.add(firstElement, trackedRoute)
        map.overlays.add(secondElement, traveledRoute)
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
            removeTrackedPathVehicle()
        }
    }

    private fun removeTrackedPathVehicle() {
        trackedRoute.actualPoints.clear()
        traveledRoute.actualPoints.clear()
    }
}