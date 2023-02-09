package com.krak.krakowautobusy.ui.map

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.krak.krakowautobusy.BuildConfig
import com.krak.krakowautobusy.databinding.MapActivityBinding
import com.krak.krakowautobusy.ui.detailsline.ActualTimeTableShowData
import com.krak.krakowautobusy.ui.detailsline.DetailsMapViewModel
import com.krak.krakowautobusy.ui.location.UserLocation
import com.krak.krakowautobusy.ui.map.vehicledata.Utilities
import com.krak.krakowautobusy.ui.position.ActualPositionVehicles
import com.krak.krakowautobusy.ui.utility.Drawables
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView

private const val TAG = "CreateDetailsMapFragment"

class CreateVehicleStopDetailsMapFragment : Fragment() {

    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    private lateinit var drawables: Drawables
    private lateinit var utilities: Utilities
    private lateinit var userLocation: UserLocation

    private val startingLattitude = 50.06173293019267
    private val startingLongtitude = 19.937894523426294

    private val minZoomLevel = 13.0
    private val maxZoomLevel = 20.0
    private val currentZoomLevel = 14.0
    private val bundleNumberLineKey="bundleKey"
    private val bundleNumberLineRequestKey="requestKey"
    private lateinit var actualPositionVehicles: ActualPositionVehicles
    private lateinit var updateTextTask: Runnable
    val mainHandler = Handler(Looper.getMainLooper())
    private val viewModel: DetailsMapViewModel by viewModels({requireParentFragment()})
    private val vieModelMy: ActualTimeTableShowData by activityViewModels()
    private var mapControllerGloval :MapController? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView
        userLocation = UserLocation(context as AppCompatActivity)
        drawables = Drawables(context as AppCompatActivity)
        utilities = Utilities(context as AppCompatActivity)
        drawables.resizeIcons(drawables, utilities, map.zoomLevel)

        val mapController = MapController(map, requireContext())
        mapController.createLocationMarker(userLocation, drawables)
        mapController.setStartingPoint(startingLattitude,startingLongtitude)
        mapController.setZoomLevels(minZoomLevel,maxZoomLevel,currentZoomLevel)
        actualPositionVehicles = ActualPositionVehicles(drawables)
        mapController.loadingIcon(actualPositionVehicles)
        mapController.createLocationMarker(userLocation, drawables)
        mapControllerGloval = mapController


        viewModel.setMyLocation.observe(viewLifecycleOwner, Observer {
            if (it) {
                enableLocalization()
                mapController.addLocationMarkerToMap(userLocation)
            } else {
                disableLocaliztaion()
                mapController.removeLocationMarkerFromMap(userLocation)
            }
        })




        drawVehicleStop()

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun drawVehicleStop(){

        val handler = Handler()
        val delay = 2000 // 1000 milliseconds == 1 second


        handler.postDelayed(object : Runnable {
            override fun run() {
                mapControllerGloval!!.addChoiceVehicleStopToMap()
               // handler.postDelayed(this, delay.toLong())
            }
        }, delay.toLong())

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun enableLocalization(){
        userLocation.getLocationUpdates(map)
        userLocation.startLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun disableLocaliztaion() {
        userLocation.stopLocationUpdates()
    }
}