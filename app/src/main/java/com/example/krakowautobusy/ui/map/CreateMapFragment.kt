package com.example.krakowautobusy.ui.map

import android.graphics.drawable.Drawable
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
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.MapActivityBinding
import com.example.krakowautobusy.ui.map.vehicledata.ActualPositionVehicles
import com.example.krakowautobusy.ui.map.vehicledata.BusStopPosition
import com.example.krakowautobusy.ui.map.vehicledata.UserLocation
import com.example.krakowautobusy.ui.map.vehicledata.Utilities
import kotlinx.coroutines.*
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.lang.Runnable

import kotlin.collections.ArrayList

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

    private lateinit var updateTextTask: Runnable
    val mainHandler = Handler(Looper.getMainLooper())

    private val MIN_ZOOM_LEVEL = 13.0
    private val MAX_ZOOM_LEVEL = 20.0
    private val CURRENT_ZOOM_LEVEL = 14.0
    private val RUNNABLE_DELAY: Long = 7000

    private val STARTING_LATTITUDE = 50.06173293019267
    private val STARTING_LONGTITUDE = 19.937894523426294

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView

        userLocation = UserLocation(context as AppCompatActivity)
        busStopPosition = BusStopPosition(context as AppCompatActivity)
        drawables = Drawables(context as AppCompatActivity)
        utilities = Utilities(context as AppCompatActivity)

        utilities.setZoomLevel(map.zoomLevel)

        actualPositionVehicles = ActualPositionVehicles(drawables)

        val mapController = MapController(map, requireContext())

        drawables.resizeIcons(drawables, utilities, map.zoomLevel)

        mapController.initialConfig()
        mapController.setZoomLevels(MIN_ZOOM_LEVEL, MAX_ZOOM_LEVEL, CURRENT_ZOOM_LEVEL)
        mapController.onZoomChangeListener(drawables, utilities)
        mapController.setStartingPoint(STARTING_LATTITUDE, STARTING_LONGTITUDE)
        mapController.drawLocationMarker(userLocation, drawables)
        mapController.drawTrackedRoute(actualPositionVehicles)

        updateTextTask = object : Runnable {
            override fun run() {
                mapController.drawAllVehicles(actualPositionVehicles)
                mainHandler.postDelayed(this, RUNNABLE_DELAY)
            }
        }
        mainHandler.post(updateTextTask)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        userLocation.setEnabled(true)
        actualPositionVehicles.setEnabled(true)
        Log.i("CreateMapFragment", "onResumeCalled")
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.i("CreateMapFragment", "onPauseCalled")
        userLocation.setEnabled(false)
        actualPositionVehicles.setEnabled(false)
        map.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainHandler.removeCallbacks(updateTextTask)
        Log.i("CreateMapFragment", "OnDestroyVewCalled")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("CreateMapFragment", "OnDestroyCalled")
    }
}