package com.example.krakowautobusy.ui.details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.krakowautobusy.BundleChoiceVehicle
import com.example.krakowautobusy.R
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.FragmentDetailsBinding
import com.example.krakowautobusy.ui.ActualTimeTableShowData
import com.example.krakowautobusy.ui.map.AdapterTimeTableListView
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.StatusData
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import com.example.krakowautobusy.ui.map.CreateDetailsMapFragment
import com.example.krakowautobusy.ui.map.vehicledata.Vehicle
import com.example.krakowautobusy.ui.map.MapViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response


class DetailsFragment : Fragment() {


    data class IdVehicle(val tripId:String,val vehicleId:String,val direction:String)

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val DEFAULT_VEHICLE_NUMBER=0;
    private val NUMBER_LINE_TOP_TEXT_FORMAT="Linia numer "//to ze stringów brać trzeba xD Kurwa Mać JA pierdole
    private  var lineNumber:Int=DEFAULT_VEHICLE_NUMBER;
    private var firstVehicleStopName=""
    private var lastVehicleStopName=""
    private val LINE_NUMBER_BUNDLE_NAME="lineNumber"
    private val mapViewModel: MapViewModel by viewModels()
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



    private var vehicleNameFromMaps:String?=null
    private var tripIdFromMaps:String?=null
    private var vehicleIdFromMaps:String?=null
    companion object{
        var numberLine:Int = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));//to obczaj
        setFragmentResultListener("details") { requestKey, bundle ->
            vehicleNameFromMaps = bundle.getString("vehicle")
            tripIdFromMaps=bundle.getString("tripId")
            vehicleIdFromMaps=bundle.getString("vehicleId")
            // Do something with the result
            // Log.i("TTTTTTT", vehicleName.toString())
        }

        binding.locationfab.setOnClickListener{
            mapViewModel.isSetLocation()
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }



       // messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));
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

        if(vehicles.size>0) {
            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId;
        }

    }


    private val viewModel: ActualTimeTableShowData by activityViewModels()

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
         //   Log.e("KURWICA",vehicles[0].tripId)


        }
        Log.e("qweqwe","Wyb"+vehicles[choiceIndex].vehicleId)
        viewModel.actualShowVehicleId.value=vehicles[choiceIndex].vehicleId;

    }



    fun setFirstVehicleAndLastInViews(actualDirection:String){
        Log.e("kurwa2",actualDirection)
        binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
        binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"

        Log.e("kurwa2",actualDirection+" / "+firstVehicleStopName)

        if(actualDirection.trim().contains(firstVehicleStopName.trim())){
            Log.e("kurwa2","TRUE")
            binding.details.currentlyFollowingFirstBusStopData.text="$lastVehicleStopName"
            binding.details.currentlyFollowingLastBusStopData.text="$firstVehicleStopName"
        }else{
            Log.e("kurwa2","FAOSE")
            binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
            binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"
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

                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }


                    var sortedBusWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    var fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()) and (!x.name.contains(direction.trim()))) {
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id,x.name))
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


        if(vehicleNameFromMaps!=null){
            for (i in 0..vehicles.size) {
                if(vehicles[i].tripId.equals(tripIdFromMaps)) //dodaj tez drugie autobusy czy tramwaje
                {
                    choiceIndex=i;
                    break;
                }
            }
            vehicleNameFromMaps=null
        }
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
                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }


                    var sortedTramWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    var fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()) and (!x.name.contains(direction.trim()))) {
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id,x.name))
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


        if(vehicleNameFromMaps!=null){
            for (i in 0..vehicles.size) {
                if(vehicles[i].tripId.equals(tripIdFromMaps)) //dodaj tez drugie autobusy czy tramwaje
                {
                    choiceIndex=i;
                    break;
                }
            }
            vehicleNameFromMaps=null
        }
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
                   viewModel.actualShowVehicleId.value=vehicles[choiceIndex].vehicleId;
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
//        viewModel.actualShowVehicleId.value=vehicles[choiceIndex].vehicleId;
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
            setFirstVehicleAndLastInViews(vehicles[choiceIndex].direction)
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