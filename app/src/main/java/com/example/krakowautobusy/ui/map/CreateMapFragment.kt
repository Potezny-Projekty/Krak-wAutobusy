package com.example.krakowautobusy.ui.map

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.MapActivityBinding
import com.example.krakowautobusy.ui.map.vehicledata.ActualPositionVehicles
import com.example.krakowautobusy.ui.map.vehicledata.BusStopPosition
import com.example.krakowautobusy.ui.map.vehicledata.UserLocation
import com.example.krakowautobusy.ui.map.vehicledata.Utilities
import com.example.krakowautobusy.ui.map.vehicledata.*
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import java.lang.Runnable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


private const val TAG = "CreateMapFragment"

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class CreateMapFragment : Fragment() {
    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    private lateinit var actualPositionVehicles: ActualPositionVehicles
    private lateinit var busStopPosition: BusStopPosition
    private lateinit var userLocation: UserLocation
    private lateinit var drawables: Drawables
    private lateinit var utilities: Utilities
    private val viewModel: MapViewModel by viewModels({requireParentFragment()})

    private val MIN_ZOOM_LEVEL = 13.0
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
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView

        initialSetup()
        setupMapView()
        //setupDrawables()
        
        enableBroadcastReceiver()
        //enableLocalization()

        viewModel.setMyLocation.observe(viewLifecycleOwner, Observer {
            enableLocalization()
            mapController.drawLocationMarker(userLocation, drawables)
        })

        return binding.root
    }

    private fun setupMapView() {
        utilities.setZoomLevel(CURRENT_ZOOM_LEVEL.toInt())

        mapController.setZoomLevels(MIN_ZOOM_LEVEL, MAX_ZOOM_LEVEL, CURRENT_ZOOM_LEVEL)
       // mapController.onZoomChangeListener(drawables, utilities)
        mapController.setStartingPoint(STARTING_LATTITUDE, STARTING_LONGTITUDE)
        //mapController.drawLocationMarker(userLocation, drawables)
        //mapController.drawTrackedRoute(actualPositionVehicles)
        mapController.startShowingVehiclesOnTheMap(viewModel.isFavourit, viewLifecycleOwner)

    }

    private fun setupDrawables() {
        drawables.resizeIcons(drawables, utilities, map.zoomLevel)
    }

    private fun initialSetup() {

        mapController = MapController(map, requireContext())
        userLocation = UserLocation(context as AppCompatActivity)
        busStopPosition = BusStopPosition(context as AppCompatActivity)
        utilities = Utilities(context as AppCompatActivity)
        drawables = Drawables(context as AppCompatActivity)
        actualPositionVehicles = ActualPositionVehicles(drawables)
    }
    private fun enableLocalization(){
        userLocation.getLocationUpdates(map)
        userLocation.startLocationUpdates()
    }
    private fun enableBroadcastReceiver(){
        val br: BroadcastReceiver = LocationProviderChangedReceiver(map,userLocation)
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        requireActivity().registerReceiver(br,filter)
    }

    override fun onResume() {
        super.onResume()
        userLocation.startLocationUpdates()
        actualPositionVehicles.setEnabled(true)
        Log.i(TAG, "onResumeCalled")
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPauseCalled")
        userLocation.stopLocationUpdates()
        actualPositionVehicles.setEnabled(false)
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
}