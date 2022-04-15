package com.example.krakowautobusy.ui.map

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.MapActivityBinding
import com.example.krakowautobusy.ui.map.vehicledata.ActualPositionVehicles
import com.example.krakowautobusy.ui.map.vehicledata.BusStopPosition
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

import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class CreateDetailsMapFragment : Fragment() {

    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView

        map.setTileSource(TileSourceFactory.MAPNIK)

        val mapController = map.controller
        // ukrycie przycisków + - zoomujących mapę
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        // odblokowanie zoomowania
        map.setMultiTouchControls(true)

        // ograniczenie zakresu do którego można przesunąć mapę
        val startingPoint2 = GeoPoint(50.3107434126593, 19.61671721450658)
        val startingPoint3 = GeoPoint(49.88512598174506, 19.545556322799532)
        val startingPoint4 = GeoPoint(50.32107434126593, 20.379321439500526)
        val startingPoint5 = GeoPoint(49.87252834809176, 20.461999509306546)

        val arrayList: ArrayList<GeoPoint> = ArrayList()
        arrayList.add(startingPoint2)
        arrayList.add(startingPoint3)
        arrayList.add(startingPoint4)
        arrayList.add(startingPoint5)

        val boundingBox = BoundingBox.fromGeoPoints(arrayList)

        map.setScrollableAreaLimitDouble(boundingBox)

        map.minZoomLevel = 13.0
        map.maxZoomLevel = 20.0

        mapController.setZoom(14.0)

        val startingPoint = GeoPoint(50.06173293019267, 19.937894523426294)

        mapController.setCenter(startingPoint)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}