package com.krak.krakowautobusy.ui.detailsline

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
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
import com.krak.krakowautobusy.ui.map.vehicledata.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.krak.krakowautobusy.ui.detailsvehiclestop.BundleVehicleStop
import com.krak.krakowautobusy.ui.position.ActualPositionVehicles
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.Runnable

data class IdVehicle(val tripId:String,val vehicleId:String,val direction:String)
private const val DELAY_FIRST_REFRESH_TIME_TABLE_AFTER_DOWNLOAD_ALL_LINES_WITH_NUMBER_DATA_MS=1500L
private const val REFRESH_TIME_TABLE_AFTER_DOWNLOAD_DATA_ALL_LINES_WITH_NUMBER_MS=5000L
private const val DELAY_CHANGE_VEHICLE_FOLLOW_FOR_UPDATE_DATA_MS=300L
private const val DELAY_REFRESH_ALL_VEHICLE_MS=15000L

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val defaultVehicleNumber=0
    private var numberLineTopTextFormat=""
    private  var lineNumber:Int=defaultVehicleNumber
    private var firstVehicleStopName=""
    private var lastVehicleStopName=""
    private val lineNumberBundleName="lineNumber"
    private val mapViewModel: DetailsMapViewModel by viewModels()
    private var adapterListTimeTable: AdapterTimeTableListView?=null
    private lateinit var refreshTimeTableAfterDownloadDataRunable:Runnable
    private lateinit var refreshListVehicleRunnable: Runnable


    val mainHandler = Handler(Looper.getMainLooper())
    private var vehicles:ArrayList<IdVehicle> = ArrayList()
    var choiceIndex:Int=0

    private var vehicleNameFromMaps:String?=null
    private var tripIdFromMaps:String?=null
    private var vehicleIdFromMaps:String?=null
    companion object{
        var numberLine:Int = 0
    }



    private fun readArgumentsAndProvideDataForMap(){
        if(arguments!=null) {
            if(requireArguments().size()>1){
                messageForMapFragment(requireArguments().getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject))
                vehicleNameFromMaps=requireArguments().getInt(BundleChoiceVehicle.LINE_NUMBER.nameBundleObject).toString()
                tripIdFromMaps=requireArguments().getString("tripId").toString()

            }else {
                messageForMapFragment(requireArguments().getInt(lineNumberBundleName))
            }


        }
    }


    private fun addFragmentResultListener(){
        val requestKeyName="details"
        val bundleVehicleNameKey="vehicle"
        val bundleTripIdKey="tripId"
        val bundleVehicleIdKey="vehicleId"


        setFragmentResultListener(requestKeyName) { _, bundle ->
            vehicleNameFromMaps = bundle.getString(bundleVehicleNameKey)
            tripIdFromMaps=bundle.getString(bundleTripIdKey)
            vehicleIdFromMaps=bundle.getString(bundleVehicleIdKey)
            messageForMapFragment(vehicleNameFromMaps!!.toInt())
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun addOnClickNavigationButton(){
        binding.locationfab.setOnClickListener{
            mapViewModel.isSetLocation()
            if (mapViewModel.setMyLocation.value!!) {
                binding.locationfab.iconTint =
                    ColorStateList.valueOf(resources.getColor(R.color.detailsFragmentColorGreen,null))
            } else {
                binding.locationfab.iconTint =
                    ColorStateList.valueOf(resources.getColor(R.color.detailsFragmentColorGray,null))
            }

            it.setBackgroundColor(resources.getColor(R.color.animFloatingButtonColor,null))

            val animIncreaseButtonFactory=1.05f
            val animDurationMS=300L
            val defaultSize=1.0f
            val defaultColor=Color.WHITE


            it.animate()
                .scaleX(animIncreaseButtonFactory).scaleY(animIncreaseButtonFactory).setDuration(animDurationMS).withEndAction {
                    it.animate().scaleX(defaultSize).scaleY(defaultSize).start()
                    it.setBackgroundColor(defaultColor)
                }.start()
        }
    }


    private fun addChangeActualVehicleFollowObserver(){
        val nameObserver = Observer<String> { newName ->

            for(i in 0 until vehicles.size){
                if(vehicles[i].vehicleId==newName){
                    tripID = vehicles[i].tripId
                    vehicleId = vehicles[i].vehicleId
                    choiceIndex=i
                }
            }


        }

        ActualPositionVehicles.actualVehicleIdClick.observe(viewLifecycleOwner,nameObserver

        )
    }


    
    
    private fun  addBackActionToArrow(){
        binding.backArrowDetailsMenu.setOnClickListener {
            mainHandler.removeCallbacks(refreshListVehicleRunnable)
            mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
            findNavController().popBackStack()
        }

    }
    
    
    private fun addToFavouriteAfterClickHeart(){
        val animScaleDownFactory=0.8f
        val normalScaleFactor=1f
        val animDurationMS=400L
        
        val animScaleUpFactory=1.2f

        binding.heartIcon.setOnClickListener {
            if(Api.getApi().isLineFavourite(numberLine)){
                Api.getApi().removeLinesFromFavourites(numberLine)
                
                binding.heartIcon.animate()
                    .scaleX(animScaleDownFactory).setDuration(animDurationMS).start()

                binding.heartIcon.animate()
                    .scaleY(animScaleDownFactory).setDuration(animDurationMS).withEndAction {
                        binding.heartIcon.animate().scaleX(normalScaleFactor).setDuration(animDurationMS).start()
                        binding.heartIcon.animate().scaleY(normalScaleFactor).setDuration(animDurationMS).start()
                        
                    }
                
                binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon)

            }else {

                binding.heartIcon.animate()
                    .scaleX(animScaleUpFactory).setDuration(animDurationMS).start()

                binding.heartIcon.animate()
                    .scaleY(animScaleUpFactory).setDuration(animDurationMS).withEndAction {
                        binding.heartIcon.animate().scaleX(normalScaleFactor).setDuration(animDurationMS).start()
                        binding.heartIcon.animate().scaleY(normalScaleFactor).setDuration(animDurationMS).withEndAction {

                        }
                    }


                binding.heartIcon.setImageResource(R.drawable.red_heart_icon)
                Api.getApi().addLineToFavourite(numberLine)
            }
        }
    }
    
    
    
    private fun readIsLineFavouriteAndSetIcon(){
        if(Api.getApi().isLineFavourite(numberLine)){
            binding.heartIcon.setImageResource(R.drawable.red_heart_icon)
        }else {
            binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon)
        }
        
    }
    
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        numberLineTopTextFormat=resources.getString(R.string.textLineNumber)
        readArgumentsAndProvideDataForMap()
        addFragmentResultListener()
        addOnClickNavigationButton()
        addChangeActualVehicleFollowObserver()
        addBackActionToArrow()
        addToFavouriteAfterClickHeart()
        readIsLineFavouriteAndSetIcon()
        
        return binding.root
    }


    private fun updateDataTimeTableAndVehiclesPositionEvery(baseDataVeh:ArrayList<IdVehicle>) = runBlocking {
        val zeroElem=0
        if(baseDataVeh.size>zeroElem) {
            vehicles = baseDataVeh
        }
        refreshTimeTable()
        refreshChoiceFollowIndexVehicle()
    }

    private var tripID:String=""
    private var vehicleId:String=""

    private val noElem=0
    private val defaultChoiceVehicleIndex=0
    
    private fun setTripIdAndVehicleToActualTrack(){
        tripID = vehicles[choiceIndex].tripId
        vehicleId = vehicles[choiceIndex].vehicleId
    }
    
    private fun refreshChoiceFollowIndexVehicle()= runBlocking {

        if(vehicles.size>noElem) {
            if(choiceIndex>vehicles.size-1){
                choiceIndex=defaultChoiceVehicleIndex
            }
            setTripIdAndVehicleToActualTrack()
        }
        
    }


    private val viewModel: ActualTimeTableShowData by activityViewModels()

    private fun changeIndexChoiceVehicleFollowToNext()= runBlocking {

        choiceIndex+=1
     
        if(choiceIndex>=vehicles.size){
            choiceIndex=defaultChoiceVehicleIndex
        }

        refreshChoiceFollowIndexVehicle()
        mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
        mainHandler.postDelayed(refreshTimeTableAfterDownloadDataRunable,DELAY_CHANGE_VEHICLE_FOLLOW_FOR_UPDATE_DATA_MS)

        if(vehicles.size>0) {
            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId
        }
        
        if(vehicles.size>choiceIndex) {
            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId
        }
    }



    private fun setFirstAndLastVehicleStopInView(actualDirection:String){
       val space=" "
       val nospace="" 
        
        binding.details.currentlyFollowingFirstBusStopData.text= firstVehicleStopName
        binding.details.currentlyFollowingLastBusStopData.text= lastVehicleStopName
        
        if(actualDirection.trim().replace(space,nospace).contains(firstVehicleStopName.trim().replace(space,nospace))){
            binding.details.currentlyFollowingFirstBusStopData.text= lastVehicleStopName
            binding.details.currentlyFollowingLastBusStopData.text= firstVehicleStopName
        }else{
            binding.details.currentlyFollowingFirstBusStopData.text= firstVehicleStopName
            binding.details.currentlyFollowingLastBusStopData.text= lastVehicleStopName
        }
    }

    
    
    private fun findSelectionVehicle(){
        if(vehicleNameFromMaps!=null){
            if(vehicles.size>0){
                for (i in 0 until vehicles.size) {
                    if(vehicles[i].tripId == tripIdFromMaps)
                    {
                        choiceIndex=i
                        break
                    }
                }
                vehicleNameFromMaps=null
            }}
    }



    
    fun downloadVehicleDataAndSetDirectionInView(nameLine: String, direction: String){
        var lastUpdateNumber=0L
        val listBaseDataVehiclesWithSpecificNumberLine:ArrayList<IdVehicle> = ArrayList()
        
        Api.getApi().getBusPosition(lastUpdateNumber,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {
                 
                    val allBus = response.body()!!
                    lastUpdateNumber = allBus.lastUpdate
                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()+" "+direction.trim())) {
                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }
                    

                    val sortedBusWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    val fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList()
                    
                    
                    val numberRegex="[^0-9]".toRegex()
                    val emptyText=""
                    for(x in allBus.vehicles){
                    
                        if(x.name.replace(numberRegex, emptyText).contains(nameLine.trim()) and (!x.name.contains(direction.trim()))
                            and (nameLine.trim().length==x.name.replace(numberRegex, emptyText).length)) {
                                
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }
                    val listVehicleFitNumberButNotDirection=fitNumberButNotDirection.sortedWith(compareBy { it.tripId.toLong() })
                    
                    if(sortedBusWithSpecificNumberLine.isNotEmpty() || listVehicleFitNumberButNotDirection.isNotEmpty()){
                        listBaseDataVehiclesWithSpecificNumberLine.clear()
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(sortedBusWithSpecificNumberLine)
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(listVehicleFitNumberButNotDirection)
                    }


                }
                updateDataTimeTableAndVehiclesPositionEvery(listBaseDataVehiclesWithSpecificNumberLine)
                refreshChoiceFollowIndexVehicle()
            }
        )

            findSelectionVehicle()
       
    }

    fun downloadTramDataAndFilterFitToActualNumberChoice(nameLine: String,direction: String){
        var lastUpdateNumber=0L
        val numberRegex="[^0-9]".toRegex()
        val emptyText=""
        val listBaseDataVehiclesWithSpecificNumberLine:ArrayList<IdVehicle> = ArrayList()
        Api.getApi().getTramPosition(lastUpdateNumber,
            fun(response: Response<AllVehicles>) {
                if (response.isSuccessful && response.body() != null ) {
                    val allBus = response.body()!!
                    val space=" "
                    lastUpdateNumber = allBus.lastUpdate

                    for(x in allBus.vehicles){
                        if(x.name.contains(nameLine.trim()+space+direction.trim())) {
                            listBaseDataVehiclesWithSpecificNumberLine.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }

                    
                    val sortedTramWithSpecificNumberLine = listBaseDataVehiclesWithSpecificNumberLine.sortedWith(compareBy { it.tripId.toLong() })

                    val fitNumberButNotDirection:ArrayList<IdVehicle> = ArrayList()
                    for(x in allBus.vehicles){
                        if(x.name.replace(numberRegex, emptyText).contains(nameLine.trim())
                            and (!x.name.contains(direction.trim()))
                            and (nameLine.trim().length==x.name.replace(numberRegex, emptyText).length)) {
                            fitNumberButNotDirection.add(IdVehicle(x.tripId, x.id,x.name))
                        }
                    }

                    val listVehicleFitNumberButNotDirection=fitNumberButNotDirection.sortedWith(compareBy { it.tripId.toLong() })

                    if(sortedTramWithSpecificNumberLine.isNotEmpty() || listVehicleFitNumberButNotDirection.isNotEmpty()){
                        listBaseDataVehiclesWithSpecificNumberLine.clear()
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(sortedTramWithSpecificNumberLine)
                        listBaseDataVehiclesWithSpecificNumberLine.addAll(listVehicleFitNumberButNotDirection)
                    }

                }
                updateDataTimeTableAndVehiclesPositionEvery(listBaseDataVehiclesWithSpecificNumberLine)
            }
        )

        findSelectionVehicle()



    }

    private fun refreshAllVehiclesBaseData(nameLine:String, direction:String){
        refreshListVehicleRunnable = object : Runnable {
            override fun run() {
                downloadVehicleDataAndSetDirectionInView(nameLine,direction)
                downloadTramDataAndFilterFitToActualNumberChoice(nameLine,direction)
                mainHandler.postDelayed(this, DELAY_REFRESH_ALL_VEHICLE_MS)
            }

        }
        mainHandler.post(refreshListVehicleRunnable)
    }



    private var isReadRequestResponse=0

    fun refreshTimeTableDataSet(){

        try {
            Api.getApi().getTimeTableBus(tripID, vehicleId, fun(response: Response<TimeTableData>) {

                val activity= activity
                if(activity!=null&& isAdded){

                viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId

                if (response.isSuccessful) {
                    val choiceVehicleTimeTable = response.body()!!

                    if (choiceVehicleTimeTable.actual.size > 0 || choiceVehicleTimeTable.old.size > 0) {

                        choiceVehicleTimeTable.old.addAll(choiceVehicleTimeTable.actual)
                        refreshChoiceFollowIndexVehicle()
                        adapterListTimeTable?.changeDataset(choiceVehicleTimeTable.old)

                    }

                }
            }})


            Api.getApi()
                .getTimeTableTram(tripID, vehicleId, fun(response: Response<TimeTableData>) {
                    val activity= activity
                    if(activity!=null&& isAdded){
                    viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId
                    if (response.isSuccessful) {
                        val choiceVehicleTimeTable = response.body()!!
                        if (choiceVehicleTimeTable.actual.size > 0 || choiceVehicleTimeTable.old.size > 0) {
                            choiceVehicleTimeTable.old.addAll(choiceVehicleTimeTable.actual)
                            adapterListTimeTable?.changeDataset(choiceVehicleTimeTable.old)
                            refreshChoiceFollowIndexVehicle()
                            viewModel.actualShowVehicleId.value = vehicles[choiceIndex].vehicleId
                        }

                    }
                }})

            isReadRequestResponse+=1
            if(adapterListTimeTable!!.count==0 &&isReadRequestResponse>1){
                binding.titlex.visibility=View.GONE
            }else{
                binding.titlex.visibility=View.VISIBLE
            }


        }catch (exp:IllegalStateException){

        }
    }
    private fun refreshTimeTable(){
        refreshTimeTableAfterDownloadDataRunable = object : Runnable {
            override fun run() {
                refreshTimeTableDataSet()
               mainHandler.postDelayed(this, REFRESH_TIME_TABLE_AFTER_DOWNLOAD_DATA_ALL_LINES_WITH_NUMBER_MS)
                }
            }

        mainHandler.postDelayed(refreshTimeTableAfterDownloadDataRunable,DELAY_FIRST_REFRESH_TIME_TABLE_AFTER_DOWNLOAD_ALL_LINES_WITH_NUMBER_DATA_MS)
    }



    private fun messageForMapFragment(numberLine:Int){
        val numberLineFromBundleKey="bundleKey"
        val directionLineBundleKey="direction"
        val requestKeyBundleFragment="requestKey"

        val result = Bundle()
        result.putInt(numberLineFromBundleKey,numberLine )
        result.putString(directionLineBundleKey,lastVehicleStopName )
        requireActivity().supportFragmentManager.setFragmentResult(requestKeyBundleFragment, result)
    }


    private fun readArguments(){
        if(arguments!=null && requireArguments().size()>0) {
            messageForMapFragment(requireArguments().getInt(lineNumberBundleName))
        }
    }


    private fun addOnClickTimeTableToMoveVehicleStopDetailsView(){
        binding.listTimeTable.setOnItemClickListener { _, view, _, _ ->
            val defaultValue=""
            val stopId=view.findViewById<TextView>(R.id.nameStopBus).tag.toString()

            val bundle = bundleOf(
                BundleVehicleStop.ID_VEHICLE_STOP.nameBundle to
                        defaultValue,
                BundleVehicleStop.NAME_VEHICLE_STOP.nameBundle to
                        view.findViewById<TextView>(R.id.nameStopBus).text.toString(),
                BundleVehicleStop.ID_STOP_POINT.nameBundle to
                        defaultValue
            )

            Navigation.findNavController(view).navigate(R.id.action_navigate_to_details_vehiclestopa,bundle)

        }
    }

    override fun onStart() {
        super.onStart()
        uncheckedAllNavMenuOption()
        getDataFromIntent()
        fillViewsDataFromBundle()
        readArguments()
        addAdapterToListViewTimeTable()
        refreshDataTimeTable()
        addOnClickTimeTableToMoveVehicleStopDetailsView()

    }


    private fun addAdapterToListViewTimeTable(){
        adapterListTimeTable= AdapterTimeTableListView(ArrayList<StatusData>(),requireContext())
        binding.listTimeTable.adapter=adapterListTimeTable
    }

    private fun refreshDataTimeTable(){
        refreshAllVehiclesBaseData(numberLine.toString(),lastVehicleStopName)
        refreshTimeTable()
    }



    private fun getDataFromIntent(){

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

            if(firstVehicleStopName.isEmpty()){
                try {
                    firstVehicleStopName = Api.getApi()
                        .getInfoAboutLineConcretDirectionLastStopName(
                            lineNumber,
                            lastVehicleStopName
                        ).firstStopName
                }catch (exp:Exception){}
            }
        }

    }


    private fun fillViewsDataFromBundle(){
        binding.lineNumberTop.text= numberLineTopTextFormat+" "+lineNumber
        binding.details.lineNumberDetailed.text="$lineNumber"
        numberLine =lineNumber
        binding.details.currentlyFollowingFirstBusStopData.text= firstVehicleStopName
        binding.details.currentlyFollowingLastBusStopData.text= lastVehicleStopName

        binding.details.changeDirectionButton.setOnClickListener {

               changeIndexChoiceVehicleFollowToNext()
               if(vehicles.size>choiceIndex){

               setFirstAndLastVehicleStopInView(vehicles[choiceIndex].direction)
        }

        }

    }

    override fun onStop() {
        super.onStop()

        mainHandler.removeCallbacks(refreshListVehicleRunnable)
        mainHandler.removeCallbacks(refreshTimeTableAfterDownloadDataRunable)
    }

    private fun uncheckedAllNavMenuOption() {
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.menu.setGroupCheckable(0, true, false)
        for (i in 0 until navView.menu.size()) {
            navView.menu.getItem(i).isChecked = false
        }
        navView.menu.setGroupCheckable(0, true, true)
    }
}