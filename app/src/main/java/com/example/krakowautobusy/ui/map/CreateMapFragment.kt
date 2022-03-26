package com.example.krakowautobusy.ui.map

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
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
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.nio.file.Files
import java.nio.file.Path

class CreateMapFragment : Fragment() {
    private lateinit var map : MapView;
    private lateinit var binding: MapActivityBinding

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
        val startingPoint2 = GeoPoint(50.13271431317449, 19.709937084370207);
        val startingPoint3 = GeoPoint(49.93777520783109, 19.760309614486303);
        val startingPoint4 = GeoPoint(50.10687584147389, 20.167067795173768);
        val startingPoint5 = GeoPoint(49.99690671441174, 20.12047320481638);


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
        val geoPoints = ArrayList<GeoPoint>()
        val line = Polyline()
        line.outlinePaint.setColor(Color.CYAN)

        geoPoints.add(GeoPoint(50.05276388888889, 19.91520388888889))
        geoPoints.add(GeoPoint(50.05275805555555, 19.915088055555557))
        geoPoints.add(GeoPoint(50.05274611111111, 19.91496))
        geoPoints.add(GeoPoint(50.052728888888886, 19.914813888888887))
        geoPoints.add(GeoPoint(50.05271388888889, 19.914693055555556))
        geoPoints.add(GeoPoint(50.052658888888885, 19.91436388888889))
        geoPoints.add(GeoPoint(50.05259194444444, 19.914018888888886))
        geoPoints.add(GeoPoint(50.05257694444444, 19.913936111111113))
        geoPoints.add(GeoPoint(50.05255388888889, 19.91383111111111))
        geoPoints.add(GeoPoint(50.05250805555555, 19.913683055555556))
        geoPoints.add(GeoPoint(50.05244194444444, 19.91348888888889))
        geoPoints.add(GeoPoint(50.052393055555555, 19.913336944444445))
        geoPoints.add(GeoPoint(50.05219694444444, 19.912636111111112))
        geoPoints.add(GeoPoint(50.05207611111111, 19.912171944444445))
        geoPoints.add(GeoPoint(50.05195305555555, 19.91168888888889))
        geoPoints.add(GeoPoint(50.05187694444444, 19.911211944444442))
        geoPoints.add(GeoPoint(50.05184694444444, 19.91075111111111))
        geoPoints.add(GeoPoint(50.0518, 19.909983055555557))
        geoPoints.add(GeoPoint(50.051755, 19.90966388888889))
        geoPoints.add(GeoPoint(50.05170194444444, 19.909428055555555))
        geoPoints.add(GeoPoint(50.05166611111111, 19.90927))
        geoPoints.add(GeoPoint(50.05160611111111, 19.909048055555555))
        geoPoints.add(GeoPoint(50.05155, 19.908908055555557))
        geoPoints.add(GeoPoint(50.051475, 19.90869888888889))
        geoPoints.add(GeoPoint(50.051411111111115, 19.908543055555555))
        geoPoints.add(GeoPoint(50.051023055555554, 19.907813055555557))
        geoPoints.add(GeoPoint(50.05035694444444, 19.906583055555554))
        geoPoints.add(GeoPoint(50.050333888888886, 19.90651))
        geoPoints.add(GeoPoint(50.05019388888889, 19.90627611111111))
        geoPoints.add(GeoPoint(50.049908055555555, 19.905886944444443))
        geoPoints.add(GeoPoint(50.049506111111114, 19.90523111111111))
        geoPoints.add(GeoPoint(50.04944194444444, 19.905101944444443))
        geoPoints.add(GeoPoint(50.049393055555555, 19.904953055555556))
        geoPoints.add(GeoPoint(50.04934611111111, 19.904766111111112))
        geoPoints.add(GeoPoint(50.04930694444444, 19.904571944444445))
        geoPoints.add(GeoPoint(50.04929694444444, 19.904518888888887))
        geoPoints.add(GeoPoint(50.04924611111111, 19.904266944444444))
        geoPoints.add(GeoPoint(50.049188888888885, 19.904121111111113))
        geoPoints.add(GeoPoint(50.04915388888889, 19.903906944444444))
        geoPoints.add(GeoPoint(50.04913805555555, 19.903831944444445))
        geoPoints.add(GeoPoint(50.04909, 19.903561944444444))
        geoPoints.add(GeoPoint(50.04920694444444, 19.903506944444445))
        geoPoints.add(GeoPoint(50.04924611111111, 19.903488055555556))
        geoPoints.add(GeoPoint(50.049371111111114, 19.903396944444445))
        geoPoints.add(GeoPoint(50.04957194444444, 19.903235))
        geoPoints.add(GeoPoint(50.04975, 19.903081111111113))
        geoPoints.add(GeoPoint(50.04982, 19.903015))
        geoPoints.add(GeoPoint(50.04982, 19.903015))
        geoPoints.add(GeoPoint(50.050046111111115, 19.902801111111113))
        geoPoints.add(GeoPoint(50.050353888888885, 19.902483055555557))
        geoPoints.add(GeoPoint(50.05069805555555, 19.90209388888889))
        geoPoints.add(GeoPoint(50.05082805555555, 19.901891944444444))
        geoPoints.add(GeoPoint(50.050978888888885, 19.901526111111114))
        geoPoints.add(GeoPoint(50.05102694444444, 19.901438055555555))
        geoPoints.add(GeoPoint(50.051085, 19.901376944444443))
        geoPoints.add(GeoPoint(50.05116888888889, 19.901338055555556))
        geoPoints.add(GeoPoint(50.051426944444444, 19.90128111111111))
        geoPoints.add(GeoPoint(50.05168194444444, 19.901235))
        geoPoints.add(GeoPoint(50.05251694444444, 19.901121944444444))
        geoPoints.add(GeoPoint(50.05304194444444, 19.901051111111112))
        geoPoints.add(GeoPoint(50.05308194444444, 19.90098888888889))
        geoPoints.add(GeoPoint(50.053128055555554, 19.900885))
        geoPoints.add(GeoPoint(50.05316611111111, 19.900711944444446))
        geoPoints.add(GeoPoint(50.05317694444444, 19.900538055555558))
        geoPoints.add(GeoPoint(50.05325888888889, 19.897695))
        geoPoints.add(GeoPoint(50.05325888888889, 19.897695))
        geoPoints.add(GeoPoint(50.053263055555554, 19.897573055555554))
        geoPoints.add(GeoPoint(50.05326694444444, 19.897425))
        geoPoints.add(GeoPoint(50.05327888888889, 19.897018888888887))
        geoPoints.add(GeoPoint(50.05327194444444, 19.896801111111113))
        geoPoints.add(GeoPoint(50.05321694444444, 19.896345))
        geoPoints.add(GeoPoint(50.05319194444444, 19.896181944444443))
        geoPoints.add(GeoPoint(50.053173888888885, 19.896061111111113))
        geoPoints.add(GeoPoint(50.05315194444444, 19.895926944444444))
        geoPoints.add(GeoPoint(50.05293611111111, 19.894506944444444))
        geoPoints.add(GeoPoint(50.05283388888889, 19.89382611111111))
        geoPoints.add(GeoPoint(50.05272305555555, 19.893108055555555))
        geoPoints.add(GeoPoint(50.05269388888889, 19.89289388888889))
        geoPoints.add(GeoPoint(50.052683055555555, 19.892693055555554))
        geoPoints.add(GeoPoint(50.05269, 19.89254111111111))
        geoPoints.add(GeoPoint(50.05272305555555, 19.892406944444446))
        geoPoints.add(GeoPoint(50.05278305555555, 19.89228111111111))
        geoPoints.add(GeoPoint(50.05287694444444, 19.892183888888887))
        geoPoints.add(GeoPoint(50.05294111111111, 19.892173888888887))
        geoPoints.add(GeoPoint(50.053015, 19.89218888888889))
        geoPoints.add(GeoPoint(50.05309194444444, 19.892266944444444))
        geoPoints.add(GeoPoint(50.053238888888885, 19.892453055555556))
        geoPoints.add(GeoPoint(50.05367888888889, 19.893233888888886))
        geoPoints.add(GeoPoint(50.05372388888889, 19.89328))
        geoPoints.add(GeoPoint(50.05379305555555, 19.893278888888887))
        geoPoints.add(GeoPoint(50.053871111111114, 19.893285))
        geoPoints.add(GeoPoint(50.053941111111115, 19.89326))
        geoPoints.add(GeoPoint(50.054315, 19.893123888888887))
        geoPoints.add(GeoPoint(50.05439194444444, 19.893096111111113))
        geoPoints.add(GeoPoint(50.05459694444444, 19.892758055555557))
        geoPoints.add(GeoPoint(50.05466, 19.89266611111111))
        geoPoints.add(GeoPoint(50.05470305555555, 19.892601944444444))
        geoPoints.add(GeoPoint(50.05477694444444, 19.892518055555556))
        geoPoints.add(GeoPoint(50.05486694444444, 19.892466944444443))
        geoPoints.add(GeoPoint(50.054925, 19.892453888888888))
        geoPoints.add(GeoPoint(50.05495194444444, 19.89256111111111))
        geoPoints.add(GeoPoint(50.054988888888886, 19.892646111111112))
        geoPoints.add(GeoPoint(50.055036111111114, 19.8927))
        geoPoints.add(GeoPoint(50.055191111111114, 19.892833055555556))
        geoPoints.add(GeoPoint(50.05523111111111, 19.892855))
        geoPoints.add(GeoPoint(50.05525611111111, 19.892843888888887))
        geoPoints.add(GeoPoint(50.05529111111111, 19.892801944444443))
        geoPoints.add(GeoPoint(50.05532888888889, 19.892743055555556))
        geoPoints.add(GeoPoint(50.05531388888889, 19.892723055555557))



        val geoPoints2 = ArrayList<GeoPoint>()
        val line2 = Polyline()

        geoPoints2.add(GeoPoint(50.05531388888889, 19.892723055555557))
        geoPoints2.add(GeoPoint(50.055235, 19.892618055555555))
        geoPoints2.add(GeoPoint(50.05513694444444, 19.892526111111113))
        geoPoints2.add(GeoPoint(50.055033888888886, 19.89246888888889))
        geoPoints2.add(GeoPoint(50.05498111111111, 19.892455))
        geoPoints2.add(GeoPoint(50.054925, 19.892453888888888))
        geoPoints2.add(GeoPoint(50.05486694444444, 19.892466944444443))
        geoPoints2.add(GeoPoint(50.05477694444444, 19.892518055555556))
        geoPoints2.add(GeoPoint(50.05470305555555, 19.892601944444444))
        geoPoints2.add(GeoPoint(50.05466, 19.89266611111111))
        geoPoints2.add(GeoPoint(50.05459694444444, 19.892758055555557))
        geoPoints2.add(GeoPoint(50.05439194444444, 19.893096111111113))
        geoPoints2.add(GeoPoint(50.054315, 19.893123888888887))
        geoPoints2.add(GeoPoint(50.053941111111115, 19.89326))
        geoPoints2.add(GeoPoint(50.053871111111114, 19.893285))
        geoPoints2.add(GeoPoint(50.05379305555555, 19.893278888888887))
        geoPoints2.add(GeoPoint(50.053753888888885, 19.893203055555556))
        geoPoints2.add(GeoPoint(50.05327305555555, 19.892348055555555))
        geoPoints2.add(GeoPoint(50.053221111111114, 19.89227888888889))
        geoPoints2.add(GeoPoint(50.053145, 19.892208055555557))
        geoPoints2.add(GeoPoint(50.05307805555555, 19.892183888888887))
        geoPoints2.add(GeoPoint(50.053015, 19.89218888888889))
        geoPoints2.add(GeoPoint(50.05294111111111, 19.892173888888887))
        geoPoints2.add(GeoPoint(50.05287694444444, 19.892183888888887))
        geoPoints2.add(GeoPoint(50.05278305555555, 19.89228111111111))
        geoPoints2.add(GeoPoint(50.05272305555555, 19.892406944444446))
        geoPoints2.add(GeoPoint(50.05269, 19.89254111111111))
        geoPoints2.add(GeoPoint(50.052683055555555, 19.892693055555554))
        geoPoints2.add(GeoPoint(50.05269388888889, 19.89289388888889))
        geoPoints2.add(GeoPoint(50.05272305555555, 19.893108055555555))
        geoPoints2.add(GeoPoint(50.05283388888889, 19.89382611111111))
        geoPoints2.add(GeoPoint(50.05293611111111, 19.894506944444444))
        geoPoints2.add(GeoPoint(50.05315194444444, 19.895926944444444))
        geoPoints2.add(GeoPoint(50.053173888888885, 19.896061111111113))
        geoPoints2.add(GeoPoint(50.05319194444444, 19.896181944444443))
        geoPoints2.add(GeoPoint(50.05321694444444, 19.896345))
        geoPoints2.add(GeoPoint(50.05327194444444, 19.896801111111113))
        geoPoints2.add(GeoPoint(50.05327888888889, 19.897018888888887))
        geoPoints2.add(GeoPoint(50.05326694444444, 19.897425))
        geoPoints2.add(GeoPoint(50.053263888888885, 19.897536944444443))
        geoPoints2.add(GeoPoint(50.053263888888885, 19.897536944444443))
        geoPoints2.add(GeoPoint(50.053263055555554, 19.897573055555554))
        geoPoints2.add(GeoPoint(50.05317694444444, 19.900538055555558))
        geoPoints2.add(GeoPoint(50.05316611111111, 19.900711944444446))
        geoPoints2.add(GeoPoint(50.053128055555554, 19.900885))
        geoPoints2.add(GeoPoint(50.05308194444444, 19.90098888888889))
        geoPoints2.add(GeoPoint(50.05304194444444, 19.901051111111112))
        geoPoints2.add(GeoPoint(50.05251694444444, 19.901121944444444))
        geoPoints2.add(GeoPoint(50.05168194444444, 19.901235))
        geoPoints2.add(GeoPoint(50.051426944444444, 19.90128111111111))
        geoPoints2.add(GeoPoint(50.05116888888889, 19.901338055555556))
        geoPoints2.add(GeoPoint(50.051085, 19.901376944444443))
        geoPoints2.add(GeoPoint(50.05102694444444, 19.901438055555555))
        geoPoints2.add(GeoPoint(50.050978888888885, 19.901526111111114))
        geoPoints2.add(GeoPoint(50.05082805555555, 19.901891944444444))
        geoPoints2.add(GeoPoint(50.05069805555555, 19.90209388888889))
        geoPoints2.add(GeoPoint(50.050353888888885, 19.902483055555557))
        geoPoints2.add(GeoPoint(50.050046111111115, 19.902801111111113))
        geoPoints2.add(GeoPoint(50.04975, 19.903081111111113))
        geoPoints2.add(GeoPoint(50.04957194444444, 19.903235))
        geoPoints2.add(GeoPoint(50.049371111111114, 19.903396944444445))
        geoPoints2.add(GeoPoint(50.04924611111111, 19.903488055555556))
        geoPoints2.add(GeoPoint(50.04920694444444, 19.903506944444445))
        geoPoints2.add(GeoPoint(50.04909, 19.903561944444444))
        geoPoints2.add(GeoPoint(50.048988055555554, 19.90357388888889))
        geoPoints2.add(GeoPoint(50.04887611111111, 19.903598888888887))
        geoPoints2.add(GeoPoint(50.04874888888889, 19.90364))
        geoPoints2.add(GeoPoint(50.048743888888886, 19.903641944444445))
        geoPoints2.add(GeoPoint(50.048743888888886, 19.903641944444445))
        geoPoints2.add(GeoPoint(50.04868388888889, 19.903671944444444))
        geoPoints2.add(GeoPoint(50.04864111111111, 19.903696944444444))
        geoPoints2.add(GeoPoint(50.04857194444444, 19.90375388888889))
        geoPoints2.add(GeoPoint(50.04849694444444, 19.903861111111112))
        geoPoints2.add(GeoPoint(50.04845805555555, 19.90395388888889))
        geoPoints2.add(GeoPoint(50.048431111111114, 19.90404611111111))
        geoPoints2.add(GeoPoint(50.048425, 19.904138055555556))
        geoPoints2.add(GeoPoint(50.04842305555555, 19.90422))
        geoPoints2.add(GeoPoint(50.04843194444444, 19.90431611111111))
        geoPoints2.add(GeoPoint(50.04845888888889, 19.904418888888888))
        geoPoints2.add(GeoPoint(50.04850194444444, 19.904508888888888))
        geoPoints2.add(GeoPoint(50.04855805555555, 19.90457111111111))
        geoPoints2.add(GeoPoint(50.04861388888889, 19.904608888888887))
        geoPoints2.add(GeoPoint(50.04950888888889, 19.905493055555556))
        geoPoints2.add(GeoPoint(50.04955611111111, 19.905541944444444))
        geoPoints2.add(GeoPoint(50.04963611111111, 19.905686944444444))
        geoPoints2.add(GeoPoint(50.05011, 19.90630388888889))
        geoPoints2.add(GeoPoint(50.050198888888886, 19.906423888888888))
        geoPoints2.add(GeoPoint(50.050308055555554, 19.906551944444445))
        geoPoints2.add(GeoPoint(50.05035694444444, 19.906583055555554))
        geoPoints2.add(GeoPoint(50.051023055555554, 19.907813055555557))
        geoPoints2.add(GeoPoint(50.051411111111115, 19.908543055555555))
        geoPoints2.add(GeoPoint(50.051475, 19.90869888888889))
        geoPoints2.add(GeoPoint(50.05155, 19.908908055555557))
        geoPoints2.add(GeoPoint(50.05160611111111, 19.909048055555555))
        geoPoints2.add(GeoPoint(50.05166611111111, 19.90927))
        geoPoints2.add(GeoPoint(50.05170194444444, 19.909428055555555))
        geoPoints2.add(GeoPoint(50.051755, 19.90966388888889))
        geoPoints2.add(GeoPoint(50.0518, 19.909983055555557))
        geoPoints2.add(GeoPoint(50.05184694444444, 19.91075111111111))
        geoPoints2.add(GeoPoint(50.05187694444444, 19.911211944444442))
        geoPoints2.add(GeoPoint(50.05195305555555, 19.91168888888889))
        geoPoints2.add(GeoPoint(50.05207611111111, 19.912171944444445))
        geoPoints2.add(GeoPoint(50.05219694444444, 19.912636111111112))
        geoPoints2.add(GeoPoint(50.052393055555555, 19.913336944444445))
        geoPoints2.add(GeoPoint(50.05244194444444, 19.91348888888889))
        geoPoints2.add(GeoPoint(50.05250805555555, 19.913683055555556))
        geoPoints2.add(GeoPoint(50.05255388888889, 19.91383111111111))
        geoPoints2.add(GeoPoint(50.05257694444444, 19.913936111111113))
        geoPoints2.add(GeoPoint(50.05259194444444, 19.914018888888886))
        geoPoints2.add(GeoPoint(50.052658888888885, 19.91436388888889))
        geoPoints2.add(GeoPoint(50.05271388888889, 19.914693055555556))
        geoPoints2.add(GeoPoint(50.052728888888886, 19.914813888888887))
        geoPoints2.add(GeoPoint(50.05274611111111, 19.91496))
        geoPoints2.add(GeoPoint(50.05275805555555, 19.915088055555557))
        geoPoints2.add(GeoPoint(50.05276611111111, 19.915263055555556))
        geoPoints2.add(GeoPoint(50.052773055555555, 19.915378055555557))
        geoPoints2.add(GeoPoint(50.052776111111115, 19.91549611111111))
        geoPoints2.add(GeoPoint(50.05277694444444, 19.915563055555555))
        geoPoints2.add(GeoPoint(50.05277694444444, 19.915616944444444))
        geoPoints2.add(GeoPoint(50.052776111111115, 19.915738055555558))
        geoPoints2.add(GeoPoint(50.052773888888886, 19.915878888888887))
        geoPoints2.add(GeoPoint(50.05277, 19.91600111111111))
        geoPoints2.add(GeoPoint(50.052765, 19.916071111111112))
        geoPoints2.add(GeoPoint(50.052753055555556, 19.916251111111112))
        geoPoints2.add(GeoPoint(50.05272694444444, 19.91649888888889))
        geoPoints2.add(GeoPoint(50.052683055555555, 19.91684111111111))
        geoPoints2.add(GeoPoint(50.052793888888885, 19.916826111111114))
        geoPoints2.add(GeoPoint(50.05293388888889, 19.916788055555557))
        geoPoints2.add(GeoPoint(50.05311305555555, 19.916745))
        geoPoints2.add(GeoPoint(50.05301388888889, 19.91658))
        geoPoints2.add(GeoPoint(50.052965, 19.9165))
        geoPoints2.add(GeoPoint(50.052881944444444, 19.916365))
        geoPoints2.add(GeoPoint(50.05280805555555, 19.916268055555555))
        geoPoints2.add(GeoPoint(50.052753055555556, 19.916251111111112))
        geoPoints2.add(GeoPoint(50.052765, 19.916071111111112))
        geoPoints2.add(GeoPoint(50.05277, 19.91600111111111))
        geoPoints2.add(GeoPoint(50.052773888888886, 19.915878888888887))
        geoPoints2.add(GeoPoint(50.052776111111115, 19.915738055555558))
        geoPoints2.add(GeoPoint(50.05277694444444, 19.915616944444444))
        geoPoints2.add(GeoPoint(50.05277694444444, 19.915563055555555))
        geoPoints2.add(GeoPoint(50.052776111111115, 19.91549611111111))
        geoPoints2.add(GeoPoint(50.052773055555555, 19.915378055555557))
        geoPoints2.add(GeoPoint(50.05276611111111, 19.915263055555556))
        geoPoints2.add(GeoPoint(50.05276388888889, 19.91520388888889))
        line2.outlinePaint.setColor(Color.RED)
        line2.setPoints(geoPoints2);
        line.setPoints(geoPoints);

        map.overlays.add(line);
        map.overlays.add(line2);
        map.invalidate()
        val marker = Marker(map)
        marker.position = startingPoint
        marker.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.bus_icon) }
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)

        showAllBusStops(map)
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
            marker.title = elem.nameBusStop+" "+elem.id
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
}