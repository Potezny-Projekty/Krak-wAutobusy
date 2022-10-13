package com.krak.krakowautobusy.ui.map

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.krak.krakowautobusy.BuildConfig
import com.krak.krakowautobusy.databinding.MapActivityBinding
import com.krak.krakowautobusy.ui.location.LocationProviderChangedReceiver
import com.krak.krakowautobusy.ui.location.UserLocation
import com.krak.krakowautobusy.ui.map.vehicledata.*
import com.krak.krakowautobusy.ui.position.ActualPositionFavouriteVehicle
import com.krak.krakowautobusy.ui.position.ActualPositionVehicles
import com.krak.krakowautobusy.ui.position.BusStopPosition
import com.krak.krakowautobusy.ui.position.BusStopPositionFavourite
import com.krak.krakowautobusy.ui.utility.Drawables
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView





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

    private val minZoomLevel = 4.0
    private val maxZoomLevel = 20.0
    private val currentZoomLevel = 14.0

    private val startingLatitude = 50.06173293019267
    private val startingLongitude = 19.937894523426294

    private lateinit var mapController: MapController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MapActivityBinding.inflate(inflater, container, false)
        map = binding.mapView
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        initialSetup()
        setupMapView()

        enableBroadcastReceiver()
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

        mapController.loadMarkerIntoMap(actualPositionVehicles)
        mapController.loadMarkerIntoMap(actualPositionFavouriteVehicle)

        viewModel.isFavourit.observe(viewLifecycleOwner, Observer {
            if (viewModel.showBusStops.value!!) {
                mapController.removeShowingBusStops(busStopPositionOrFavouriteBusStopPosition)
                switchBetweenFavourtieAndStandardMap(it)
                mapController.showAllBusStops(busStopPositionOrFavouriteBusStopPosition)
            } else {
                mapController.removeCallback()
                mapController.removeShowingAllVehicles(actualPositionAllOrFavouriteVehicle)
                mapController.removeTrackedVehicle(actualPositionAllOrFavouriteVehicle)

                switchBetweenFavourtieAndStandardMap(it)

                mapController.showAllVehicleMarker(actualPositionAllOrFavouriteVehicle)
                mapController.startShowVehicle(actualPositionAllOrFavouriteVehicle)
            }
        })
        return binding.root
    }

    private fun setupMapView() {
        utilities.setZoomLevel(currentZoomLevel.toInt())

        mapController.setZoomLevels(minZoomLevel, maxZoomLevel, currentZoomLevel)
        mapController.setStartingPoint(startingLatitude, startingLongitude)
        mapController.startShowVehicle(actualPositionAllOrFavouriteVehicle)

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
        map.onResume()
    }

    override fun onPause() {
        super.onPause()

        userLocation.stopLocationUpdates()
        actualPositionAllOrFavouriteVehicle.setEnabled(false)
        map.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapController.removeCallback()

    }



    private fun switchBetweenBusStopsAndVehicle(isBusStops : Boolean) {
        if (isBusStops) {
            mapController.removeCallback()
            mapController.removeShowingAllVehicles(actualPositionAllOrFavouriteVehicle)
            mapController.removeTrackedVehicle(actualPositionAllOrFavouriteVehicle)
            mapController.showAllBusStops(busStopPositionOrFavouriteBusStopPosition)

        } else {
            mapController.startShowVehicle(actualPositionAllOrFavouriteVehicle)
            mapController.removeShowingBusStops(busStopPositionOrFavouriteBusStopPosition)
            mapController.showAllVehicleMarker(actualPositionAllOrFavouriteVehicle)
        }
    }

    private fun switchBetweenFavourtieAndStandardMap(isFavourite : Boolean) {
        if (isFavourite) {
            busStopPositionOrFavouriteBusStopPosition = busStopPositionFavourite
            actualPositionAllOrFavouriteVehicle = actualPositionFavouriteVehicle
        } else {
            busStopPositionOrFavouriteBusStopPosition = busStopPosition
            actualPositionAllOrFavouriteVehicle = actualPositionVehicles
        }
    }
}