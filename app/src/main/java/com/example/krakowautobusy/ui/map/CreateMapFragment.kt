package com.example.krakowautobusy.ui.map

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.R
import com.example.krakowautobusy.database.Database
import com.example.krakowautobusy.database.Select_db_BusStop
import com.example.krakowautobusy.databinding.MapActivityBinding
import kotlinx.coroutines.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.collections.ArrayList




class CreateMapFragment : Fragment() {
    private lateinit var map : MapView;
    private lateinit var binding: MapActivityBinding
    val mainHandler = Handler(Looper.getMainLooper())
    private lateinit var updateTextTask : Runnable


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
//        val startingPoint2 = GeoPoint(50.13271431317449, 19.709937084370207)
//        val startingPoint3 = GeoPoint(49.93777520783109, 19.760309614486303)
//        val startingPoint4 = GeoPoint(50.10687584147389, 20.167067795173768)
//        val startingPoint5 = GeoPoint(49.99690671441174, 20.12047320481638)
        val startingPoint2 = GeoPoint(50.3107434126593, 19.61671721450658)
        val startingPoint3 = GeoPoint(49.88512598174506, 19.545556322799532)
        val startingPoint4 = GeoPoint(50.32107434126593, 20.379321439500526)
        val startingPoint5 = GeoPoint(49.87252834809176, 20.461999509306546)

        val arrayList: ArrayList<GeoPoint> = ArrayList()
        arrayList.add(startingPoint2)
        arrayList.add(startingPoint3)
        arrayList.add(startingPoint4)
        arrayList.add(startingPoint5)

        val boundingBox =  BoundingBox.fromGeoPoints(arrayList)

        map.setScrollableAreaLimitDouble(boundingBox)
        map.minZoomLevel = 13.5
        map.maxZoomLevel = 20.0

        mapController.setZoom(14.0)

        val startingPoint = GeoPoint(50.06173293019267, 19.937894523426294);

        mapController.setCenter(startingPoint);
        val marker = Marker(map)
        marker.position = startingPoint
        marker.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.bus_icon) }
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)
        map.overlays.add(marker)
        map.overlays.add(marker)
        //showAllBusStops(map)
        val actualPositionVehicles = ActualPositionVehicles()
        actualPositionVehicles.showAllVehicle(map, context)
        updateTextTask = object : Runnable {
            override fun run() {
                actualPositionVehicles.showAllVehicle(map, context)
                mainHandler.postDelayed(this, 5000)
            }
        }
        mainHandler.post(updateTextTask)
        map.invalidate()
        return binding.root
    }


    fun showAllBusStops(map:MapView){
        val vv= Select_db_BusStop()
        val x= Database.getInstance(requireActivity())
        val xx= vv.selectBusStopAll(x.readableDatabase)//,8095258289119440510L

        for(elem in xx){
            Log.e("gg",elem.nameBusStop+ " "+(elem.longitude/3600000f).toString())
            val startingPoint = GeoPoint((elem.latitude/3600000f).toDouble(), (elem.longitude/3600000f).toDouble());

            val marker = Marker(map)
            marker.position = startingPoint
            marker.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.bus_icon) }
            marker.title = elem.nameBusStop+" "+elem.id.toString()
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            map.overlays.add(marker)
            //map.invalidate()
        }



    }



    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause();
        map.onPause();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("AAA", "Usuniecie apk")
        mainHandler.removeCallbacks(updateTextTask)
    }
}