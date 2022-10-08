package com.krak.krakowautobusy.ui.details

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.krak.krakowautobusy.BundleChoiceVehicle
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.databinding.FragmentDetailsBinding
import com.krak.krakowautobusy.ui.ActualTimeTableShowData
import com.krak.krakowautobusy.ui.map.AdapterTimeTableListView
import com.krak.krakowautobusy.ui.map.DetailsMapViewModel
import com.krak.krakowautobusy.ui.map.vehicledata.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.krak.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.Runnable


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
    private val mapViewModel: DetailsMapViewModel by viewModels()
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
        //messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));//to obczaj


            if(arguments!=null) {
                if(requireArguments().size()>1){

                  //  Log.e("szczegoly",":D"+requireArguments().getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject)+" / "+ arguments!!.size())
                    messageForMapFragment(requireArguments().getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject))
                    vehicleNameFromMaps=requireArguments().getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject).toString()
                    tripIdFromMaps=requireArguments().getString("tripId").toString()


                  //  vehicleIdFromMaps=requireArguments().getString("vehicleId").toString()
                }else {
                    messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));//to obczaj
                }


            }

        setFragmentResultListener("details") { requestKey, bundle ->
            vehicleNameFromMaps = bundle.getString("vehicle")
            vehicleNameFromMaps?.let { Log.e("szczegoly", it) }
            tripIdFromMaps=bundle.getString("tripId")
            vehicleIdFromMaps=bundle.getString("vehicleId")
            messageForMapFragment(vehicleNameFromMaps!!.toInt());//to obczaj
           // messageForMapFragment(vehicleNameFromMaps!!.toInt());//to obczaj

            // Do something with the result
            // Log.i("TTTTTTT", vehicleName.toString())
        }

        val colorGray = "#FF757575"
        val colorGreen = "#32CD32"

        binding.locationfab.setOnClickListener{
            mapViewModel.isSetLocation()
            if (mapViewModel.setMyLocation.value!!) {
                binding.locationfab.iconTint =
                    ColorStateList.valueOf(Color.parseColor(colorGreen))
            } else {
                binding.locationfab.iconTint =
                    ColorStateList.valueOf(Color.parseColor(colorGray))
            }
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

        val nameObserver = Observer<String> { newName ->
            Log.e("ez",newName)

            for(i in 0..vehicles.size-1){
                if(vehicles[i].vehicleId==newName){
                    tripID = vehicles[i].tripId
                    vehicleId = vehicles[i].vehicleId
                    choiceIndex=i
                }
            }



        }

        ActualPositionVehicles.actualVehicleIdClick.observe(viewLifecycleOwner,nameObserver

        )


        binding.backArrowDetailsMenu.setOnClickListener {
            mainHandler.removeCallbacks(refreshListVehicleRunnable)
            mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
            findNavController().popBackStack()
        }



        binding.heartIcon.setOnClickListener {
            if(Api.getApi().isLineFavourite(numberLine)){
                Api.getApi().removeLinesFromFavourites(numberLine)


                binding.heartIcon!!.animate()
                    .scaleX(0.8f).setDuration(400).start()

                binding.heartIcon!!.animate()
                    .scaleY(0.8f).setDuration(400).withEndAction {
                        binding.heartIcon.animate().scaleX(1.0f).setDuration(400).start()
                        binding.heartIcon.animate().scaleY(1.0f).setDuration(400).start()


                    }


                binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon);

            }else {

                binding.heartIcon!!.animate()
                    .scaleX(1.2f).setDuration(400).start()

                binding.heartIcon!!.animate()
                    .scaleY(1.2f).setDuration(400).withEndAction {
                        binding.heartIcon.animate().scaleX(1.0f).setDuration(400).start()
                        binding.heartIcon.animate().scaleY(1.0f).setDuration(400).withEndAction {

                        }
                    }


                binding.heartIcon.setImageResource(R.drawable.red_heart_icon);
                Api.getApi().addLineToFavourite(numberLine)
            }
        }


        if(Api.getApi().isLineFavourite(numberLine)){

            binding.heartIcon.setImageResource(R.drawable.red_heart_icon);

        }else {
            binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon);

        }

        // messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }




    fun updateAllVehicleBaseData(baseDataVeh:ArrayList<IdVehicle>) = runBlocking {
        if(baseDataVeh.size>0) {
            vehicles = baseDataVeh
        }
        Log.e("kk","Aktualizacja"+vehicles.size)
        refreshTimeTable()//hmm
        withContext(Dispatchers.Default) {


        }
        refreshChoiceFollowIndexVehicle()
    }

    var tripID:String="";
    var vehicleId:String="";

    val NO_ELEM=0
    val DEFAULT_CHOICE_VEHICLE_INDEX=0
    fun refreshChoiceFollowIndexVehicle()= runBlocking {

        if(vehicles.size>NO_ELEM) {
            if(choiceIndex>vehicles.size-1){
                choiceIndex=DEFAULT_CHOICE_VEHICLE_INDEX
                Log.e("kk","Kurwa default")
            }
            tripID = vehicles[choiceIndex].tripId
            vehicleId = vehicles[choiceIndex].vehicleId
            Log.e("kk","UPDate"+tripID+"/"+vehicleId+"?"+choiceIndex)
        }


        withContext(Dispatchers.Default) {

        }



    }


    private val viewModel: ActualTimeTableShowData by activityViewModels()

    fun changeIndexChoiceVehicleFollow()= runBlocking {

        choiceIndex+=1;
        Log.e("kk","Indeks:"+choiceIndex+" size "+vehicles.size)
        if(choiceIndex>=vehicles.size){
            choiceIndex=DEFAULT_CHOICE_VEHICLE_INDEX
        }

        refreshChoiceFollowIndexVehicle()


      //  mainHandler.removeCallbacks(refreshListVehicleRunnable)
        mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
      //  mainHandler.post { refreshListVehicleRunnable }
        mainHandler.postDelayed(refreshTimeTableAfterDownloadDataRunable,DELAY_CHANGE_VEHICLE_FOLLOW_FOR_UPDATE_DATA_MS)
        withContext(Dispatchers.Default) {



         //   Log.e("KURWICA",vehicles[0].tripId)


        }

        if(vehicles.size>0) {
            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId;
        }


        //<ci-1

        if(vehicles.size>choiceIndex) {
//        Log.e("qweqwe","Wyb"+vehicles[choiceIndex].vehicleId)
            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId;
        }
    }



    fun setFirstVehicleAndLastInViews(actualDirection:String){
        Log.e("kurwa2",actualDirection)
        binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
        binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"

        Log.e("kurwa2",actualDirection+" / "+firstVehicleStopName)

        if(actualDirection.trim().replace(" ","").contains(firstVehicleStopName.trim().replace(" ",""))){
            Log.e("kurwa2","TRUE")
            binding.details.currentlyFollowingFirstBusStopData.text="$lastVehicleStopName"
            binding.details.currentlyFollowingLastBusStopData.text="$firstVehicleStopName"
        }else{
            Log.e("kurwa2","FAOSE")
            binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
            binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"
        }
    }


    //Kierunek się nie zgadza xD
    fun downloadBusDataAndFilterFitToActualNumberChoice(nameLine: String,direction: String){
        var e_=0L
        var listBaseDataVehiclesWithSpecificNumberLine:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
        Api.getApi().getBusPosition(e_,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {
                    Log.e("kk","wczesny update")
                    val allBus = response.body()!!
                    e_ = allBus.lastUpdate

                    for(x in allBus.vehicles){

                        if(x.name.contains(nameLine.trim()+" "+direction.trim())) {

                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }
                    Log.e("kurwa9","1:"+listBaseDataVehiclesWithSpecificNumberLine.size)


                    var sortedBusWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    var fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                    for(x in allBus.vehicles){
                        Log.e("kurwa7",x.name.replace("[^0-9]".toRegex(), "")+" vs "+nameLine+"   = "+x.name.replace("[^0-9]".toRegex(), "").contains(nameLine.trim()))
                        //if(x.name.replace("[^0-9]".toRegex(), "").contains(nameLine.trim()) and (!x.name.contains(direction.trim())) and (nameLine.trim().length==x.name.replace("[^0-9]".toRegex(), "").length))
                        if(x.name.replace("[^0-9]".toRegex(), "").contains(nameLine.trim()) and (!x.name.contains(direction.trim())) and (nameLine.trim().length==x.name.replace("[^0-9]".toRegex(), "").length)) {
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }
                    var listVehicleFitNumberButNotDirection=fitNumberButNotDirection.sortedWith(compareBy { it.tripId.toLong() })
                    Log.e("kurwa9","2:"+listVehicleFitNumberButNotDirection.size)
                    if(sortedBusWithSpecificNumberLine.isNotEmpty() || listVehicleFitNumberButNotDirection.isNotEmpty()){
                        listBaseDataVehiclesWithSpecificNumberLine.clear()
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(sortedBusWithSpecificNumberLine)
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(listVehicleFitNumberButNotDirection)
                    }


                    Log.e("kk","Specyficzny : "+listBaseDataVehiclesWithSpecificNumberLine.size)

                }
                updateAllVehicleBaseData(listBaseDataVehiclesWithSpecificNumberLine)
                refreshChoiceFollowIndexVehicle()
            }
        )


        if(vehicleNameFromMaps!=null){

            Log.e("szczegoly"," "+vehicles.size)
            if(vehicles.size>0){
            for (i in 0..vehicles.size-1) {
                if(vehicles[i].tripId.equals(tripIdFromMaps)) //dodaj tez drugie autobusy czy tramwaje
                {
                    Log.e("szczegoly"," dd"+vehicles[i].direction)
                //    lastVehicleStopName= vehicles[i].direction;
                    choiceIndex=i;
                    break;
                }
            }
            vehicleNameFromMaps=null
        }}
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

                    Log.e("kurwa10","1:"+listBaseDataVehiclesWithSpecificNumberLine.size)
                    var sortedTramWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    var fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList<IdVehicle>()
                    for(x in allBus.vehicles){
                        Log.e("kurwa6",x.name.replace("[^0-9]".toRegex(), "")+" vs "+nameLine+"   = "+x.name.replace("[^0-9]".toRegex(), "").contains(nameLine.trim())+"          &&   "
                        +nameLine.trim().length+" vs "+x.name.replace("[^0-9]".toRegex(), "").length+ "  =  "+
                                (nameLine.trim().length==x.name.replace("[^0-9]".toRegex(), "").length).toString()

                        )
                        //string.replace("[^0-9]".toRegex(), "")
                        Log.e("kurwa5",x.name.replace("[^0-9]".toRegex(), "")+":"+nameLine.trim()+"         |  "+
                                (!x.name.contains(direction.trim()))+"       |        "+nameLine.trim().length+"/"+x.name.replace("[^0-9]".toRegex(), "").length
                        )
                        if(x.name.replace("[^0-9]".toRegex(), "").contains(nameLine.trim())
                            and (!x.name.contains(direction.trim()))
                            and (nameLine.trim().length==x.name.replace("[^0-9]".toRegex(), "").length)) {
                            Log.e("kurwa6","PASUJEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")

                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }
                    var listVehicleFitNumberButNotDirection=fitNumberButNotDirection.sortedWith(compareBy { it.tripId.toLong() })
                    Log.e("kurwa10","1:"+listVehicleFitNumberButNotDirection.size)
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
            if(vehicles.size>0) {
                for (i in 0..vehicles.size-1) {
                    if (vehicles[i].tripId.equals(tripIdFromMaps)) //dodaj tez drugie autobusy czy tramwaje
                    {
                        lastVehicleStopName= vehicles[i].direction;
                       // binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
                       // binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"
                        choiceIndex = i;
                        break;
                    }
                }
                vehicleNameFromMaps = null
            }        }
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



    var x=0;

    fun refreshTimeTableDataSet(){

        try {
            //   refreshChoiceFollowIndexVehicle()
            Api.getApi().getTimeTableBus(tripID, vehicleId, fun(response: Response<TimeTableData>) {

                var activity=getActivity()
                if(activity!=null&&isAdded()){
                    // ur code here


                viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId;
                Log.e("akt", "Ponrano" + tripID + "/ " + vehicleId)
                Log.e("akt", response.body().toString())
                if (response.isSuccessful) {
                    val ChoiceVehicleTimeTable = response.body()!!
                    Log.e("akt", "Ponrano2")
                    if (ChoiceVehicleTimeTable.actual.size > 0 || ChoiceVehicleTimeTable.old.size > 0) {
                        Log.e("akt", "Ponrano3")
                        ChoiceVehicleTimeTable.old.addAll(ChoiceVehicleTimeTable.actual)
                        refreshChoiceFollowIndexVehicle()
                        Log.e("akt", (adapterListTimeTable == null).toString())
                        Log.e("akt", ChoiceVehicleTimeTable.old.size.toString())
                        adapterListTimeTable?.changeDataset(ChoiceVehicleTimeTable.old)
                        //    refreshChoiceFollowIndexVehicle()
                    }

                }
            }})


            Api.getApi()
                .getTimeTableTram(tripID, vehicleId, fun(response: Response<TimeTableData>) {
                    var activity=getActivity()
                    if(activity!=null&&isAdded()){
                    viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId;
                    if (response.isSuccessful) {
                        val ChoiceVehicleTimeTable = response.body()!!
                        if (ChoiceVehicleTimeTable.actual.size > 0 || ChoiceVehicleTimeTable.old.size > 0) {
                            ChoiceVehicleTimeTable.old.addAll(ChoiceVehicleTimeTable.actual)
                            adapterListTimeTable?.changeDataset(ChoiceVehicleTimeTable.old)
                            refreshChoiceFollowIndexVehicle()
                            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId;
                        }

                    }
                }})

            x+=1;
            if(adapterListTimeTable!!.count==0 &&x>1){
                binding.titlex.visibility=View.GONE
            }else{
                binding.titlex.visibility=View.VISIBLE
            }


        }catch (exp:IllegalStateException){

        }
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

        if(arguments!=null && requireArguments().size()>0) {
            messageForMapFragment(requireArguments().getInt(LINE_NUMBER_BUNDLE_NAME));
        }


        addAdapterToListViewTimeTable()
        refreshDataTimeTable()


        binding.listTimeTable.setOnItemClickListener { _, view, _, _ ->


            val bundle = bundleOf(
                Bundle_Vehicle_Stop.ID_VEHICLE_STOP.nameBundle to
                       "",
                Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                        view.findViewById<TextView>(R.id.nameStopBus).text.toString(),
                Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                        ""

            )
            //bundle
            Navigation.findNavController(view).navigate(R.id.action_navigate_to_details_vehiclestopa,bundle);





        }

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


        if(arguments!=null && requireArguments().size()>0) {
            lineNumber =
                arguments?.getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject).toString()
                    .toInt()
            firstVehicleStopName =
                arguments?.getString(BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject)
                    .toString()
            lastVehicleStopName =
                arguments?.getString(BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject)
                    .toString()

            if(firstVehicleStopName.length==0){
                try {
                    Log.e(
                        "kurwa3",
                        Api.getApi().getInfoAboutLineConcretDirectionLastStopName(
                            lineNumber,
                            lastVehicleStopName
                        ).lastStopName
                    )
                    firstVehicleStopName = Api.getApi()
                        .getInfoAboutLineConcretDirectionLastStopName(
                            lineNumber,
                            lastVehicleStopName
                        ).firstStopName
                }catch (exp:Exception){}
            }
        }

    }


    fun fillViewsDataFromBundle(){
        binding.lineNumberTop.text= "$NUMBER_LINE_TOP_TEXT_FORMAT$lineNumber"
        binding.details.lineNumberDetailed.text="$lineNumber"
        numberLine =lineNumber
        binding.details.currentlyFollowingFirstBusStopData.text="$firstVehicleStopName"
        binding.details.currentlyFollowingLastBusStopData.text="$lastVehicleStopName"

        binding.details.changeDirectionButton.setOnClickListener {


            Log.e("kk","qw")
               changeIndexChoiceVehicleFollow()
               if(vehicles.size>choiceIndex){

               setFirstVehicleAndLastInViews(vehicles[choiceIndex].direction)
        }

        }

    }

    override fun onStop() {
        super.onStop()
          mainHandler.removeCallbacks(refreshListVehicleRunnable)
        mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
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