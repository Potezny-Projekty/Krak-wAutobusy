package com.example.krakowautobusy.ui.map

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.databinding.MapActivityBinding
import com.example.krakowautobusy.ui.map.vehicledata.*
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView



private const val TAG = "CreateMapFragment"

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class CreateMapFragment : Fragment() {
    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    private lateinit var actualPositionVehicles: ActualPositionVehicles
    private lateinit var actualPositionAllOrFavouriteVehicle: ActualPositionVehicles
    private lateinit var actualPositionFavouriteVehicle: ActualPositionFavouriteVehicle
    private lateinit var busStopPosition: BusStopPosition
    private lateinit var busStopPositionOrFavouriteBusStopPosition: BusStopPosition
    private lateinit var busStopPositionFavourite: BusStopPositionFavourite
    private lateinit var userLocation: UserLocation
    private lateinit var drawables: Drawables
    private lateinit var utilities: Utilities
    private val viewModel: MapViewModel by activityViewModels()

    private val MIN_ZOOM_LEVEL = 4.0
    private val MAX_ZOOM_LEVEL = 20.0
    private val CURRENT_ZOOM_LEVEL = 14.0

    private val STARTING_LATTITUDE = 50.06173293019267
    private val STARTING_LONGTITUDE = 19.937894523426294

    private lateinit var mapController: MapController



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView")
        binding = MapActivityBinding.inflate(inflater, container, false)
        map = binding.mapView
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        initialSetup()
        setupMapView()
        //setupDrawables()
        enableBroadcastReceiver()
        //enableLocalization()
        mapController.createLocationMarker(userLocation, drawables)
        mapController.createAllBusStopsMarker(busStopPosition)

        viewModel.setMyLocation.observe(viewLifecycleOwner, Observer {
            if (it) {
                enableLocalization()
                mapController.addLocationMarkerToMap(userLocation)
            } else {
                disableLocaliztaion()
                mapController.removeLocationMarkerFromMap(userLocation)
            }
        })

        viewModel.showBusStops.observe(viewLifecycleOwner, Observer {
            switchBetweenBusStopsAndVehicle(it)
        })

        mapController.loadMarkerIntoMap(actualPositionAllOrFavouriteVehicle)
        mapController.loadMarkerIntoMap(actualPositionFavouriteVehicle)

        viewModel.isFavourit.observe(viewLifecycleOwner, Observer {
            if (viewModel.showBusStops.value!!) {
                busStopPositionOrFavouriteBusStopPosition.hiddenAllBusStops(map)
                if (it) {
                    busStopPositionOrFavouriteBusStopPosition = busStopPositionFavourite
                    actualPositionAllOrFavouriteVehicle = actualPositionFavouriteVehicle
                } else {
                    busStopPositionOrFavouriteBusStopPosition = busStopPosition
                    actualPositionAllOrFavouriteVehicle = actualPositionVehicles
                }
                busStopPositionOrFavouriteBusStopPosition.showAllBusStops(map)
            } else {
                actualPositionAllOrFavouriteVehicle.hiddenMarkers(map)
                actualPositionAllOrFavouriteVehicle.removeTrackedVehicle()
                if (it) {
                    busStopPositionOrFavouriteBusStopPosition = busStopPositionFavourite
                    actualPositionAllOrFavouriteVehicle = actualPositionFavouriteVehicle
                } else {
                    busStopPositionOrFavouriteBusStopPosition = busStopPosition
                    actualPositionAllOrFavouriteVehicle = actualPositionVehicles
                }
                actualPositionAllOrFavouriteVehicle.showMarkers(map)
                mapController.showAllBus(actualPositionAllOrFavouriteVehicle)
                mapController.showAllTram(actualPositionAllOrFavouriteVehicle)
            }
        })
        switchBetweenBusStopsAndVehicle(viewModel.showBusStops.value!!)
        return binding.root
    }

    private fun setupMapView() {
        utilities.setZoomLevel(CURRENT_ZOOM_LEVEL.toInt())

        mapController.setZoomLevels(MIN_ZOOM_LEVEL, MAX_ZOOM_LEVEL, CURRENT_ZOOM_LEVEL)
       // mapController.onZoomChangeListener(drawables, utilities)
        mapController.setStartingPoint(STARTING_LATTITUDE, STARTING_LONGTITUDE)
        //mapController.drawLocationMarker(userLocation, drawables)
        //mapController.drawTrackedRoute(actualPositionVehicles)
        mapController.startShowVehicle(actualPositionAllOrFavouriteVehicle)

    }

    private fun setupDrawables() {
        drawables.resizeIcons(drawables, utilities, map.zoomLevel)
    }

    private fun initialSetup() {

        mapController = MapController(map, requireContext())
        userLocation = UserLocation(context as AppCompatActivity)
        utilities = Utilities(context as AppCompatActivity)
        drawables = Drawables(context as AppCompatActivity)
        actualPositionVehicles = ActualPositionVehicles(drawables)
        actualPositionFavouriteVehicle = ActualPositionFavouriteVehicle(drawables)
        busStopPosition = BusStopPosition(drawables.vehicleStopIcon)
        busStopPositionFavourite = BusStopPositionFavourite(drawables.vehicleStopIcon)
        if (viewModel.isFavourit.value!!) {
            busStopPositionOrFavouriteBusStopPosition = busStopPositionFavourite
            actualPositionAllOrFavouriteVehicle = actualPositionFavouriteVehicle
        } else {
            busStopPositionOrFavouriteBusStopPosition = busStopPosition
            actualPositionAllOrFavouriteVehicle = actualPositionVehicles
        }

    }
    private fun enableLocalization(){
        userLocation.getLocationUpdates(map)
        userLocation.startLocationUpdates()
    }

    private fun disableLocaliztaion() {
        userLocation.stopLocationUpdates()
    }

    private fun enableBroadcastReceiver(){
        val br: BroadcastReceiver = LocationProviderChangedReceiver(map,userLocation)
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        requireActivity().registerReceiver(br,filter)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResumeCalled")
        map.onResume()

    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPauseCalled")
        userLocation.stopLocationUpdates()
        actualPositionAllOrFavouriteVehicle.setEnabled(false)
        map.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapController.removeCallback()
        Log.i(TAG, "OnDestroyVewCalled")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "OnDestroyCalled")
    }

    private fun switchBetweenBusStopsAndVehicle(isBusStops : Boolean) {
        if (isBusStops) {
            mapController.removeShowingAllVehicles(actualPositionAllOrFavouriteVehicle)
            mapController.removeTrackedVehicle(actualPositionAllOrFavouriteVehicle)
            mapController.showAllBusStops(busStopPositionOrFavouriteBusStopPosition)
            mapController.removeCallback()

        } else {
            mapController.removeShowingBusStops(busStopPositionOrFavouriteBusStopPosition)
            mapController.luchCallback()
            mapController.showAllVehicleMarker(actualPositionAllOrFavouriteVehicle)

        }
    }
}