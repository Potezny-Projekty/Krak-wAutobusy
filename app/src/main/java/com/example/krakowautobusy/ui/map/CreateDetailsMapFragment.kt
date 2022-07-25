package com.example.krakowautobusy.ui.map

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
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.MapActivityBinding
import com.example.krakowautobusy.ui.map.vehicledata.ActualPositionVehicles
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import com.example.krakowautobusy.ui.map.vehicledata.Utilities
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import retrofit2.Response

private const val TAG = "CreateDetailsMapFragment"

@Suppress("DEPRECATION")
class CreateDetailsMapFragment : Fragment() {

    private lateinit var map: MapView
    private lateinit var binding: MapActivityBinding

    private lateinit var drawables: Drawables
    private lateinit var utilities: Utilities

    private val STARTING_LATTITUDE = 50.06173293019267
    private val STARTING_LONGTITUDE = 19.937894523426294

    private val MIN_ZOOM_LEVEL = 13.0
    private val MAX_ZOOM_LEVEL = 20.0
    private val CURRENT_ZOOM_LEVEL = 14.0
    private val BUNDLE_NUMBER_LINE_KEY="bundleKey"
    private val BUNDLE_NUMBER_LINE_REQUEST_KEY="requestKey"
    private lateinit var actualPositionVehicles:ActualPositionVehicles
    private lateinit var updateTextTask: Runnable
    val mainHandler = Handler(Looper.getMainLooper())
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapActivityBinding.inflate(inflater, container, false)
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        map = binding.mapView

        drawables = Drawables(context as AppCompatActivity)
        utilities = Utilities(context as AppCompatActivity)
        drawables.resizeIcons(drawables, utilities, map.zoomLevel)

        val mapController = MapController(map, requireContext())


        mapController.setStartingPoint(STARTING_LATTITUDE,STARTING_LONGTITUDE)
        mapController.setZoomLevels(MIN_ZOOM_LEVEL,MAX_ZOOM_LEVEL,CURRENT_ZOOM_LEVEL)
        actualPositionVehicles = ActualPositionVehicles(drawables)
        mapController.drawTrackedRoute(actualPositionVehicles)
        readMessageNumberLineFromTopFragment()




        return binding.root
    }


    public fun drawVehicleStopLines(numberLine:Int){
     var lineData=   Api.getApi().getVehicleStopLines(numberLine);

    for( x in lineData){
        Log.e("vvv",x.nameVehicleStop)
    }


    }

//to wyciać
    fun showTimeTableLine(nameLine:String,direction:String){
        var zm:Long=0
        Api.getApi().getBusPosition(zm,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {
                    val allBus = response.body()!!
                    zm = allBus.lastUpdate

                   for(x in allBus.vehicles){
                    //   Log.e("ole",nameLine+" "+direction+"| vs |"+x.name+"|")
                       if(x.name.contains(nameLine.trim()+" "+direction.trim())){
                           Log.e("ole",x.id+" / "+x.tripId+" "+x.name)
                           Log.e("ole",nameLine+" "+direction)



                           Api.getApi().getTimeTableBus(x.tripId,x.id, fun(response: Response<TimeTableData>)  {
                               Log.e("ole",response.raw().request().url().toString())
                               //Log.e("ole",response.raw().request(). toString())
                               Log.e("ole",response.raw().request().headers() .toString())
                               Log.e("ole",response.errorBody().toString())
                               Log.e("ole",response.message(). toString())
                               Log.e("ole",response.headers().toString()  )
                               if (response.isSuccessful) {
                                   val ac = response.body()!!

                                //   Log.e("ole",ac.old[0].name+" KUR")
                               }
                           })


                       }
                   }
                  //  showAllVehicle(map, response.body()!!)
                    Log.i("ERRORR2", allBus.toString())
                }
            }
        )


        Api.getApi().getTramPosition(zm, fun(response: Response<AllVehicles>)  {
            if (response.isSuccessful) {
                val allTram = response.body()!!
                zm = allTram.lastUpdate

                for(x in allTram.vehicles){
                    if(x.name.contains(nameLine)){
                        Log.e("ole",x.id+" / "+x.tripId)
                    }
                }
               // showAllVehicle(map, allTram)
            }
        })
    }


    fun readMessageNumberLineFromTopFragment(){
        requireActivity().supportFragmentManager
            .setFragmentResultListener(BUNDLE_NUMBER_LINE_REQUEST_KEY, this
            ) { _, bundle ->

                val result = bundle.getInt(BUNDLE_NUMBER_LINE_KEY)
                val direction = bundle.getString("direction")
                var lineData = Api.getApi().getVehicleStopLines(result);
                actualPositionVehicles.drawAllVehiclesStopLineOnMap(lineData, map);

                if (direction != null) {
                    showTimeTableLine(result.toString(),direction)
                }


                updateTextTask = object : Runnable {
                    override fun run() {
                        actualPositionVehicles.showNumberLine(map, result.toString())
                        mainHandler.postDelayed(this, 4000)
                    }
                }
                mainHandler.post(updateTextTask)

                map.invalidate()

            }
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