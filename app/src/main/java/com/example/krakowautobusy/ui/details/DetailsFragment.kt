package com.example.krakowautobusy.ui.details

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.BundleChoiceVehicle
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.FragmentDetailsBinding
import com.example.krakowautobusy.ui.map.AdapterTimeTableListView
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.StatusData
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
    private lateinit var refreshTimeTableAfterDownloadDataRunable:Runnable
    private lateinit var refreshListVehicleRunnable: Runnable
    val DELAY_FIRST_REFRESH_TIME_TABLE_AFTER_DOWNLOAD_ALL_LINES_WITH_NUMBER_DATA_MS=1500L
    val REFRESH_TIME_TABLE_AFTER_DOWNLOAD_DATA_ALL_LINES_WITH_NUMBER_MS=5000L
    val DELAY_CHANGE_VEHICLE_FOLLOW_FOR_UPDATE_DATA_MS=300L
    val DELAY_REFRESH_ALL_VEHICLE_MS=15000L
    
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




    fun updateAllVehicleBaseData(baseDataVeh:ArrayList<IdVehicle>) = runBlocking {
        withContext(Dispatchers.Default) {
            vehicles=baseDataVeh;
        }
    }

    var tripID:String="";
    var vehicleId:String="";

    val NO_ELEM=0
    val DEFAULT_CHOICE_VEHICLE_INDEX=0
    fun refreshChoiceFollowIndexVehicle()= runBlocking {
        withContext(Dispatchers.Default) {
            if(vehicles.size>NO_ELEM) {
                if(choiceIndex>vehicles.size-1){
                    choiceIndex=DEFAULT_CHOICE_VEHICLE_INDEX
                }
                tripID = vehicles[choiceIndex].tripId
                vehicleId = vehicles[choiceIndex].vehicleId
            }
        }
    }




    fun changeIndexChoiceVehicleFollow()= runBlocking {
        withContext(Dispatchers.Default) {
            choiceIndex+=1;
                if(choiceIndex>vehicles.size-1){
                    choiceIndex=DEFAULT_CHOICE_VEHICLE_INDEX
                }

            mainHandler.removeCallbacks(refreshListVehicleRunnable)
            mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
            mainHandler.post { refreshListVehicleRunnable }
            mainHandler.postDelayed(refreshTimeTableAfterDownloadDataRunable,DELAY_CHANGE_VEHICLE_FOLLOW_FOR_UPDATE_DATA_MS)
        }
    }



    fun downloadBusDataAndFilterFitToActualNumberChoice(nameLine: String,direction: String){
        var e_=0L
        var listBaseDataVehiclesWithSpecificNumberLine:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
        Api.getApi().getBusPosition(e_,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {

                    val allBus = response.body()!!
                    e_ = allBus.lastUpdate

                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()+" "+direction.trim())) {
                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id))
                        }
                    }


                    var sortedBusWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    var fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()) and (!x.name.contains(direction.trim()))) {
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id))
                        }
                    }
                    var listVehicleFitNumberButNotDirection=fitNumberButNotDirection.sortedWith(compareBy { it.tripId.toLong() })

                    if(sortedBusWithSpecificNumberLine.isNotEmpty() || listVehicleFitNumberButNotDirection.isNotEmpty()){
                        listBaseDataVehiclesWithSpecificNumberLine.clear()
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(sortedBusWithSpecificNumberLine)
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(listVehicleFitNumberButNotDirection)
                    }


                }
                updateAllVehicleBaseData(listBaseDataVehiclesWithSpecificNumberLine)
            }
        )
    }

    fun downloadTramDataAndFilterFitToActualNumberChoice(nameLine: String,direction: String){
        var e_=0L
        var listBaseDataVehiclesWithSpecificNumberLine:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
        Api.getApi().getTramPosition(e_,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {

                    val allBus = response.body()!!
                    e_ = allBus.lastUpdate

                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()+" "+direction.trim())) {
                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id))
                        }
                    }


                    var sortedTramWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    var fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()) and (!x.name.contains(direction.trim()))) {
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id))
                        }
                    }
                    var listVehicleFitNumberButNotDirection=fitNumberButNotDirection.sortedWith(compareBy { it.tripId.toLong() })

                    if(sortedTramWithSpecificNumberLine.isNotEmpty() || listVehicleFitNumberButNotDirection.isNotEmpty()){
                        listBaseDataVehiclesWithSpecificNumberLine.clear()
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(sortedTramWithSpecificNumberLine)
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(listVehicleFitNumberButNotDirection)
                    }


                }
                updateAllVehicleBaseData(listBaseDataVehiclesWithSpecificNumberLine)
            }
        )
    }

    fun refreshAllVehiclesBaseData(nameLine:String, direction:String){
        refreshListVehicleRunnable = object : Runnable {
            override fun run() {
                downloadBusDataAndFilterFitToActualNumberChoice(nameLine,direction)
                downloadTramDataAndFilterFitToActualNumberChoice(nameLine,direction)
                mainHandler.postDelayed(this, DELAY_REFRESH_ALL_VEHICLE_MS)
            }
        }
        mainHandler.post(refreshListVehicleRunnable)
    }


    fun refreshTimeTableDataSet(){
        refreshChoiceFollowIndexVehicle()
        Api.getApi().getTimeTableBus(tripID,vehicleId, fun(response: Response<TimeTableData>)  {

            if (response.isSuccessful) {
                val ChoiceVehicleTimeTable = response.body()!!
                if(ChoiceVehicleTimeTable.actual.size>0 || ChoiceVehicleTimeTable.old.size>0) {
                    ChoiceVehicleTimeTable.old.addAll(ChoiceVehicleTimeTable.actual)
                    adapterListTimeTable?.changeDataset(ChoiceVehicleTimeTable.old)
                }

            }
        })


        Api.getApi().getTimeTableTram(tripID,vehicleId, fun(response: Response<TimeTableData>)  {

            if (response.isSuccessful) {
                val ChoiceVehicleTimeTable = response.body()!!
                if(ChoiceVehicleTimeTable.actual.size>0 || ChoiceVehicleTimeTable.old.size>0) {
                    ChoiceVehicleTimeTable.old.addAll(ChoiceVehicleTimeTable.actual)
                    adapterListTimeTable?.changeDataset(ChoiceVehicleTimeTable.old)
                }

            }
        })
    }
    fun refreshTimeTable(){
        refreshTimeTableAfterDownloadDataRunable = object : Runnable {
            override fun run() {
                refreshTimeTableDataSet()
                mainHandler.postDelayed(this, REFRESH_TIME_TABLE_AFTER_DOWNLOAD_DATA_ALL_LINES_WITH_NUMBER_MS)
                }
            }

        mainHandler.postDelayed(refreshTimeTableAfterDownloadDataRunable,DELAY_FIRST_REFRESH_TIME_TABLE_AFTER_DOWNLOAD_ALL_LINES_WITH_NUMBER_DATA_MS)
    }



    fun messageForMapFragment(numberLine:Int){
        val NUMBER_LINE_BUNDLE_KEY="bundleKey"
        val DIRECTION_LINE_BUNDLE_KEY="direction"
        val REQUEST_KEY_FRAGMENT="requestKey"

        val result = Bundle()
        result.putInt(NUMBER_LINE_BUNDLE_KEY,numberLine )
        result.putString(DIRECTION_LINE_BUNDLE_KEY,lastVehicleStopName )
        requireActivity().supportFragmentManager.setFragmentResult(REQUEST_KEY_FRAGMENT, result)
    }

    override fun onStart() {
        super.onStart()
        uncheckedAllNavMenuOption()
        getDataFromIntent()
        fillViewsDataFromBundle()
        messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));
        addAdapterToListViewTimeTable()
        refreshDataTimeTable()

    }


    fun addAdapterToListViewTimeTable(){
        adapterListTimeTable=AdapterTimeTableListView(ArrayList<StatusData>(),requireContext())
        binding.listTimeTable.adapter=adapterListTimeTable
    }

    fun refreshDataTimeTable(){
        refreshAllVehiclesBaseData(numberLine.toString(),lastVehicleStopName)
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
        numberLine =lineNumber
        binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
        binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"

        binding.details.changeDirectionButton.setOnClickListener {
               changeIndexChoiceVehicleFollow()
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