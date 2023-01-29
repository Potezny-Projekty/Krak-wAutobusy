package com.krak.krakowautobusy.ui.detailsvehiclestop

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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.krak.krakowautobusy.BundleChoiceVehicle
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.databinding.FragmentVehicleStopDetailsBinding
import com.krak.krakowautobusy.networkttss.Depart
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class VehicleStopDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding get() = _binding!!
    private  var  adapter: AdapterListViewDepatures?=null

    val mainHandler = Handler(Looper.getMainLooper())
    private var _binding: FragmentVehicleStopDetailsBinding? = null


    private lateinit var timerRefreshDepartureList:Runnable
    private val timeRefreshDeparturesList=10500L
    private var idStopPoint:String=""
    private var nameVehicleStop:String=""
    private val refreshTimeTableMs=3500L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private fun refreshListDepeartures(){
        timerRefreshDepartureList = object : Runnable {
            override fun run() {



                if(idStopPoint.isEmpty()){
                    var departe:ArrayList<Depart> = ArrayList()
                    Api.getApi().getBusDepartures(Api.getApi().getVehicleStopIdByName(nameVehicleStop)
                    ){ response ->
                        try {
                            adapter = AdapterListViewDepatures(response.body()!!.actual, requireContext())
                            adapter!!.changeDataset(response.body()!!.actual)
                            departe.addAll(response.body()!!.actual)
                            binding.listdetailed.adapter = adapter


                            if (response.body()!!.actual.size == 0) {
                                binding.ifwehavedata.visibility = View.VISIBLE
                            } else {
                                binding.ifwehavedata.visibility = View.GONE
                            }

                        }catch (x:Exception){

                        }

                    }

                    Api.getApi().getTramDepartures(Api.getApi().getVehicleStopIdByName(nameVehicleStop)){ response ->
                        try {
                            adapter = AdapterListViewDepatures(response.body()!!.actual, requireContext())
                            departe.addAll(response.body()!!.actual)
                            adapter!!.changeDataset(departe)
                            binding.listdetailed.adapter = adapter


                            if (response.body()!!.actual.size == 0) {
                                binding.ifwehavedata.visibility = View.VISIBLE
                            } else {
                                binding.ifwehavedata.visibility = View.GONE
                            }

                        }catch (x:Exception){

                        }

                    }

                }else {
                    Log.e("idStop","KURWA"+idStopPoint)
                    var departe:ArrayList<Depart> = ArrayList()

                    Api.getApi().getBusDepartures(
                        idStopPoint
                    ) { response ->


                        try {

                            adapter =
                                AdapterListViewDepatures(response.body()!!.actual, requireContext())
                            departe=response.body()!!.actual
                            adapter!!.changeDataset(response.body()!!.actual)
                            binding.listdetailed.adapter = adapter

                            if (response.body()!!.actual.size == 0) {
                                binding.ifwehavedata.visibility = View.VISIBLE
                            } else {
                                binding.ifwehavedata.visibility = View.GONE
                            }

                        } catch (x: Exception) {

                        }

                    }

                    Api.getApi().getTramDepartures(Api.getApi().getVehicleStopIdByName(nameVehicleStop)){ response ->
                        try {
                            departe.addAll(response.body()!!.actual)
                            adapter = AdapterListViewDepatures(response.body()!!.actual, requireContext())
                            adapter!!.changeDataset(departe)
                            binding.listdetailed.adapter = adapter


                            if (response.body()!!.actual.size == 0) {
                                binding.ifwehavedata.visibility = View.VISIBLE
                            } else {
                                binding.ifwehavedata.visibility = View.GONE
                            }

                        }catch (x:Exception){

                        }

                    }
                }

                if (adapter == null || adapter!!
                        .count==0) {

                    binding.ifwehavedata.visibility = View.VISIBLE
                } else {
                    binding.ifwehavedata.visibility = View.GONE
                }


                mainHandler.postDelayed(this,timeRefreshDeparturesList )


            }
        }

        mainHandler.postDelayed(timerRefreshDepartureList,refreshTimeTableMs)
    }


/*
    !!!!!!!!!!!!!!!!!!!!!!!!!!!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!
    !!!!UWAGA
    UWAGA
    Te metody co z serca usuwają i dodają
    do ulubionych nie działają dobrze
    bo prawdopodobniej przez to
    ze kilka przystanków nazywa sie tak
    samo. Po poprawcę nazw trzeba to sprawdzić


 */

    private fun readDataFromBundle(){
        idStopPoint=requireArguments().getString(BundleVehicleStop.ID_STOP_POINT .nameBundle).toString()
        Log.e("idStop",":"+idStopPoint)
        nameVehicleStop=requireArguments().getString(BundleVehicleStop.NAME_VEHICLE_STOP.nameBundle).toString()
    }

    private fun addActionBackArrow(){
        binding.backArrowDetailsMenu.setOnClickListener {
            mainHandler.removeCallbacks(timerRefreshDepartureList)
            findNavController().popBackStack()
        }

    }


    private fun addAnimAddAddRemoveFromfavouriteToHeartIcon(){
        val scaleDownFactorAnim=0.8f
        val scaleNormalFactorAnim=1f
        val scaleUpFactorAnim=1.2f
        val animDurationMs=400L

        binding.heartIcon.setOnClickListener {
            if(Api.getApi().isVehicleStopFavourite(binding.lineNumberTop.text.toString())){


                binding.heartIcon.animate()
                    .scaleX(scaleDownFactorAnim).setDuration(animDurationMs).start()

                binding.heartIcon.animate()
                    .scaleY(scaleDownFactorAnim).setDuration(animDurationMs).withEndAction {
                        binding.heartIcon.animate().scaleX(scaleNormalFactorAnim).setDuration(animDurationMs).start()
                        binding.heartIcon.animate().scaleY(scaleNormalFactorAnim).setDuration(animDurationMs).withEndAction {

                        }
                    }


                binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon)

              //  Api.getApi().removeVehicleStopFromFavouriteById(idStopPoint)

               Api.getApi().removeVehicleStopFromFavourite(binding.lineNumberTop.text.toString())
            }else{

                binding.heartIcon.animate()
                    .scaleX(scaleUpFactorAnim).setDuration(animDurationMs).start()

                binding.heartIcon.animate()
                    .scaleY(scaleUpFactorAnim).setDuration(animDurationMs).withEndAction {
                        binding.heartIcon.animate().scaleX(scaleNormalFactorAnim).setDuration(animDurationMs).start()
                        binding.heartIcon.animate().scaleY(scaleNormalFactorAnim).setDuration(animDurationMs).withEndAction {

                        }
                    }

                binding.heartIcon.setImageResource(R.drawable.red_heart_icon)
               // Api.getApi().addVehicleStopToFavorite(idStopPoint.toLong())
                Api.getApi().addVehicleStopToFavorite(binding.lineNumberTop.text.toString())
            }

        }
    }


    private fun readIsVehicleStopFavouriteAndCheckOrNotIcon(){

        if(Api.getApi().isVehicleStopFavourite(binding.lineNumberTop.text.toString())){
            binding.heartIcon.setImageResource(R.drawable.red_heart_icon)

        }else{
            binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon)

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setFormatDateAndTimeInViews(){
        val fullDayNameFormat="EEEE"
        val fullDateFormat="dd.MM.yyyy"
        val current = LocalDateTime.now()
        val formatter2 = DateTimeFormatter.ofPattern(fullDayNameFormat)
        val formatted2 = current.format(formatter2)
        binding.fullNameDayView.text=formatted2
        val formatter = DateTimeFormatter.ofPattern(fullDateFormat)
        val formatted = current.format(formatter)
        binding.fullDayView.text=formatted
    }



    private fun setOnClikListElemToMoveDetailedLine(){
        val defaultValue=""

        binding.listdetailed.setOnItemClickListener { _, view, _, _ ->
            val bundle = bundleOf(
                BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                        view.findViewById<TextView>(R.id.lineNumber).text.toString().trim().toInt(),
                BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                        to defaultValue,
                BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                        view.findViewById<TextView>(R.id.nameLineDirection).text.toString()

            )

            Navigation.findNavController(view).navigate(R.id.action_vehicle_details_departures_to_details_line,bundle)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVehicleStopDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fillViewDataFromBundle()
        addDeparturesToListView(requireArguments().getString(BundleVehicleStop.ID_STOP_POINT .nameBundle).toString())
        readDataFromBundle()
        refreshListDepeartures()
        addActionBackArrow()
        addAnimAddAddRemoveFromfavouriteToHeartIcon()
        readIsVehicleStopFavouriteAndCheckOrNotIcon()
        setFormatDateAndTimeInViews()
        setOnClikListElemToMoveDetailedLine()

        return root
    }

    override fun onStop() {
        super.onStop()
        mainHandler.removeCallbacksAndMessages(null)
    }

    private fun addDeparturesToListView(idstopPoint:String){
    addAdapter(idstopPoint)

    }

    private fun addAdapter(idstopPoint:String){

        Api.getApi().getBusDepartures(idstopPoint
        ) { response ->

            try {
                adapter = AdapterListViewDepatures(response.body()!!.actual, requireContext())
                adapter!!.changeDataset(response.body()!!.actual)
                binding.listdetailed.adapter = adapter

                if (response.body()!!.actual.size == 0) {
                    binding.ifwehavedata.visibility = View.VISIBLE
                } else {
                    binding.ifwehavedata.visibility = View.GONE
                }

            }catch (x:Exception){

            }

        }


    }




    private fun fillViewDataFromBundle( ){
        binding.lineNumberTop.text= requireArguments().getString(BundleVehicleStop.NAME_VEHICLE_STOP.nameBundle).toString()

    }


}