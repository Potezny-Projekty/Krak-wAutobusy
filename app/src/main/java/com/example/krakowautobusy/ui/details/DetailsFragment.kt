package com.example.krakowautobusy.ui.details

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BundleChoiceVehicle
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.FragmentDetailsBinding
import com.example.krakowautobusy.ui.map.AdapterTimeTableListView
import com.example.krakowautobusy.ui.map.CreateDetailsMapFragment
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.StatusData
import com.example.krakowautobusy.ui.map.vehicledata.StopData
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response


class DetailsFragment : Fragment() {


    data class IdVehicle(val tripId:String,val vehicleId:String)

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val DEFAULT_VEHICLE_NUMBER=0;
    private val NUMBER_LINE_TOP_TEXT_FORMAT="Linia numer "//to ze stringów brać trzeba xD Kurwa Mać JA pierdole
    private  var lineNumber:Int=DEFAULT_VEHICLE_NUMBER;
    private var firstVehicleStopName=""
    private var lastVehicleStopName=""
    private val LINE_NUMBER_BUNDLE_NAME="lineNumber"
    private var adapterListTimeTable:AdapterTimeTableListView?=null
    private lateinit var refreshTimeTableRuna:Runnable
    private lateinit var refreshListVehicleRunnable: Runnable
    val mainHandler = Handler(Looper.getMainLooper())
    var vehicles:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
    var choiceIndex:Int=0
    //var actualBus:ArrayList<StatusData>=ArrayList<StatusData>()

    companion object{
        var numberLine:Int = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }




    fun updateList(newList:ArrayList<IdVehicle>) = runBlocking {
        withContext(Dispatchers.Default) {
            vehicles=newList;
        }
    }

    var tripID:String="";
    var vehicleId:String="";
    fun refreshTimeTableBlock()= runBlocking {
        withContext(Dispatchers.Default) {
            if(vehicles.size>0) {
                if(choiceIndex>vehicles.size-1){
                    choiceIndex=0;
                }
                tripID = vehicles[choiceIndex].tripId
                vehicleId = vehicles[choiceIndex].vehicleId
            }
        }
    }

    fun changeIndex()= runBlocking {
        withContext(Dispatchers.Default) {
            choiceIndex+=1;

                if(choiceIndex>vehicles.size-1){
                    choiceIndex=0;
                }


        }
    }


    fun refreshListVehicle(nameLine:String,direction:String){
        refreshListVehicleRunnable = object : Runnable {
            override fun run() {

                var zm=6L;

                var listVeh:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                Api.getApi().getBusPosition(zm,
                    fun(response: Response<AllVehicles>) {
                        if (response.isSuccessful && response.body() != null ) {

                            val allBus = response.body()!!
                            zm = allBus.lastUpdate
                            Log.e("ole","KURWA2")
                            for(x in allBus.vehicles){
                                if(x.name.contains(nameLine.trim()+" "+direction.trim())) {
                                    listVeh.add(IdVehicle(x.tripId, x.id))

                                }
                            }
                            var listVehs = listVeh.sortedWith(compareBy({ it.tripId.toLong() }))

                            var listVeh2:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                            for(x in allBus.vehicles){
                                if(x.name.contains(nameLine.trim()) and (!x.name.contains(direction.trim()))) {
                                    listVeh2.add(IdVehicle(x.tripId, x.id))
                                }
                            }
                            var listVehs2=listVeh2.sortedWith(compareBy({ it.tripId.toLong() }))

                            listVeh.clear()
                            listVeh.addAll(listVehs)
                            listVeh.addAll(listVehs2)

                        }
                    }
                )
               updateList(listVeh)



                mainHandler.postDelayed(this, 60000)
            }


            // mainHandler.postDelayed(this,5000L)
            // mainHandler;
        }

        mainHandler.post(refreshListVehicleRunnable)
    }



    fun refreshTimeTableBaseList(){
        refreshTimeTableBlock()
        Api.getApi().getTimeTableVehicle(tripID,vehicleId, fun(response: Response<TimeTableData>)  {
            Log.e("ole",response.raw().request().url().toString())
            //Log.e("ole",response.raw().request(). toString())
            Log.e("ole",response.raw().request().headers() .toString())
            Log.e("ole",response.errorBody().toString())
            Log.e("ole",response.message(). toString())
            Log.e("ole",response.headers().toString()  )
            if (response.isSuccessful) {
                val ac = response.body()!!
                //  ac.actual.addAll(ac.old)
                Log.e("oleK",ac.actual.size .toString())
                for(x in ac.actual){
                    Log.e("oleK",x.actualTime)
                }
                ac.old.addAll(ac.actual)
                adapterListTimeTable?.changeDataset(ac.old)


                //   Log.e("ole",ac.old[0].name+" KUR")
            }
        })
    }




    fun refreshTimeTable(){
        refreshTimeTableRuna = object : Runnable {
            override fun run() {


                refreshTimeTableBaseList()



                mainHandler.postDelayed(this, 10000)
                }


               // mainHandler.postDelayed(this,5000L)
               // mainHandler;
            }

        mainHandler.post(refreshTimeTableRuna)
    }

 /*   fun showTimeTableLine(nameLine:String,direction:String){
        var zm:Long=0
        Log.e("ole","KURWA")
        Api.getApi().getBusPosition(zm,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {
                    val allBus = response.body()!!
                    zm = allBus.lastUpdate
                    Log.e("ole","KURWA2")
                    for(x in allBus.vehicles){

                           Log.e("ole",nameLine+" "+direction+"| vs |"+x.name+"|")
                        if(x.name.contains(nameLine.trim()+" "+direction.trim())){
                            Log.e("ole",x.id+" / "+x.tripId+" "+x.name)
                            Log.e("ole",nameLine+" "+direction)



                            Api.getApi().getTimeTableVehicle(x.tripId,x.id, fun(response: Response<TimeTableData>)  {
                                Log.e("ole",response.raw().request().url().toString())
                                //Log.e("ole",response.raw().request(). toString())
                                Log.e("ole",response.raw().request().headers() .toString())
                                Log.e("ole",response.errorBody().toString())
                                Log.e("ole",response.message(). toString())
                                Log.e("ole",response.headers().toString()  )
                                if (response.isSuccessful) {
                                    val ac = response.body()!!
                                  //  ac.actual.addAll(ac.old)
                                    Log.e("oleK",ac.actual.size .toString())
                                    for(x in ac.actual){
                                        Log.e("oleK",x.actualTime)
                                    }
                                    ac.old.addAll(ac.actual)
                                    adapterListTimeTable?.changeDataset(ac.old)

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
    }*/

    fun messageForMapFragment(numberLine:Int){
        val result = Bundle()
        result.putInt("bundleKey",numberLine )
        result.putString("direction",lastVehicleStopName )
        requireActivity().supportFragmentManager.setFragmentResult("requestKey", result)
    }

    override fun onStart() {
        super.onStart()
        uncheckedAllNavMenuOption()
        getDataFromIntent()
        fillViewsDataFromBundle()
        messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));

        adapterListTimeTable=AdapterTimeTableListView(ArrayList<StatusData>(),requireContext())
        binding.listTimeTable.adapter=adapterListTimeTable
        Log.e("ole", numberLine.toString()+" "+lastVehicleStopName)
     //   showTimeTableLine(numberLine.toString(),lastVehicleStopName)
       // refreshTimeTable(numberLine.toString(),lastVehicleStopName)

        refreshListVehicle(numberLine.toString(),lastVehicleStopName)
        refreshTimeTable()
    }



    fun getDataFromIntent(){
      lineNumber=  arguments?.getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject)!!.toInt()
        firstVehicleStopName=arguments?.getString(BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject).toString()
        lastVehicleStopName=arguments?.getString(BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject).toString()
    }


    fun fillViewsDataFromBundle(){
        binding.lineNumberTop.text= "$NUMBER_LINE_TOP_TEXT_FORMAT$lineNumber"
        binding.details.lineNumberDetailed.text="$lineNumber"
        DetailsFragment.numberLine=lineNumber
        binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
        binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"

        binding.details.changeDirectionButton.setOnClickListener {
               changeIndex()
        }


    }

    fun uncheckedAllNavMenuOption() {
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.menu.setGroupCheckable(0, true, false)
        for (i in 0 until navView.menu.size()) {
            navView.menu.getItem(i).isChecked = false
        }
        navView.menu.setGroupCheckable(0, true, true)
    }
}