package com.krak.krakowautobusy.ui.map.vehicledata

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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.krak.krakowautobusy.BundleChoiceVehicle
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.databinding.FragmentVehicleStopDetailsBinding
import com.krak.krakowautobusy.ui.vehiclestop.AdapterListViewDepatures
import com.krak.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VehicleStopDetails.newInstance] factory method to
 * create an instance of this fragment.
 */


//!! USUN TIMERY PO ONCLOSE
//ACTUAL TIM TO TERAZ
class VehicleStopDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding get() = _binding!!
    private  var  adapter:AdapterListViewDepatures?=null

    val mainHandler = Handler(Looper.getMainLooper())
    val mainHandler2 = Handler(Looper.getMainLooper())
    private var _binding: FragmentVehicleStopDetailsBinding? = null



    private lateinit var timerRefreshDepartureList:Runnable
    private val TIMER_REFRESH_DEPARTURE_LIST=10500L
    private var idStopPoint:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    fun refreshListDepeartures(){
        timerRefreshDepartureList = object : Runnable {
            override fun run() {

                Log.e("ojej","Rozmiar:"+idStopPoint)
                Api.getApi().getBusDepartures(idStopPoint
                ) { response ->
                    Log.e("ojej","Rozmiar:"+response.body()!!.actual.size)
                    //  for(x in response.body()!!.actual){
                    //     Log.e("ojej",x.plannedTime.toString())
                    //  }
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


                mainHandler.postDelayed(this,TIMER_REFRESH_DEPARTURE_LIST )

            }
        }

        mainHandler.postDelayed(timerRefreshDepartureList,2000)
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVehicleStopDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fillViewDataFromBundle()
        addDeparturesToListView(requireArguments().getString(Bundle_Vehicle_Stop.ID_STOP_POINT .nameBundle).toString())
       idStopPoint=requireArguments().getString(Bundle_Vehicle_Stop.ID_STOP_POINT .nameBundle).toString()
        Log.e("aax",":"+idStopPoint)

        refreshListDepeartures()


        binding.backArrowDetailsMenu.setOnClickListener {
            mainHandler.removeCallbacks(timerRefreshDepartureList)
            findNavController().popBackStack()
        }

        binding.heartIcon.setOnClickListener {
            if(Api.getApi().isVehicleStopFavourite(binding.lineNumberTop.text.toString())){


                binding.heartIcon!!.animate()
                    .scaleX(0.8f).setDuration(400).start()

                binding.heartIcon!!.animate()
                    .scaleY(0.8f).setDuration(400).withEndAction {
                        binding.heartIcon.animate().scaleX(1.0f).setDuration(400).start()
                        binding.heartIcon.animate().scaleY(1.0f).setDuration(400).withEndAction {

                        }
                    }



                binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon);
                Api.getApi().removeVehicleStopFromFavourite(binding.lineNumberTop.text.toString())
            }else{

                binding.heartIcon!!.animate()
                    .scaleX(1.2f).setDuration(400).start()

                binding.heartIcon!!.animate()
                    .scaleY(1.2f).setDuration(400).withEndAction {
                        binding.heartIcon.animate().scaleX(1.0f).setDuration(400).start()
                        binding.heartIcon.animate().scaleY(1.0f).setDuration(400).withEndAction {

                        }
                    }

                binding.heartIcon.setImageResource(R.drawable.red_heart_icon);
                Api.getApi().addVehicleStopToFavorite(binding.lineNumberTop.text.toString())
            }

        }

        if(Api.getApi().isVehicleStopFavourite(binding.lineNumberTop.text.toString())){
            binding.heartIcon.setImageResource(R.drawable.red_heart_icon);
         //   Api.getApi().removeVehicleStopFromFavourite(binding.lineNumberTop.text.toString())
        }else{
            binding.heartIcon.setImageResource(R.drawable.ic_gray_hert_icon);
          //  Api.getApi().addVehicleStopToFavorite(binding.lineNumberTop.text.toString())
        }



    val current = LocalDateTime.now()

    val formatter2 = DateTimeFormatter.ofPattern("EEEE")
    val formatted2 = current.format(formatter2)
    binding.ca.text=formatted2;
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formatted = current.format(formatter)

    binding.vfg.text=formatted;






    binding.listdetailed.setOnItemClickListener { _, view, _, _ ->
        val bundle = bundleOf(
            BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                view.findViewById<TextView>(R.id.lineNumber).text.toString().trim().toInt(),
            BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                    to "",
            BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                    view.findViewById<TextView>(R.id.nameLineDirection).text.toString()

        )

        Navigation.findNavController(view).navigate(R.id.action_vehicle_details_departures_to_details_line,bundle);
    }








        return root
    }

    override fun onStop() {
        super.onStop()
        mainHandler.removeCallbacksAndMessages(null);
      //  mainHandler2.removeCallbacksAndMessages(null);
    }

    fun addDeparturesToListView(idstopPoint:String){
    addAdapter(idstopPoint)

    /*    Api.getApi().getBusDepartures(idstopPoint
        ) { response ->
            Log.e("ojej","Rozmiar:"+response.body()!!.actual.size)
            for(x in response.body()!!.actual){
                Log.e("ojej",x.plannedTime.toString())
            }

        }*/

    }

    fun addAdapter(idstopPoint:String){
        Log.e("aax",idstopPoint)
        Api.getApi().getBusDepartures(idstopPoint
        ) { response ->
            Log.e("ojej","Rozmiar:"+response.body()!!.actual.size)
          //  for(x in response.body()!!.actual){
           //     Log.e("ojej",x.plannedTime.toString())
          //  }
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






    fun fillViewDataFromBundle( ){

        binding.lineNumberTop.text= requireArguments().getString(Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle).toString()


    }

/*


       val bundle = bundleOf(
                    BundleChoiceVehicle.LINE_NUMBER.nameBundleObject to
                            vehicle.name.split(' ')[0].trim().toInt(),
                    BundleChoiceVehicle.FIRST_STOP_VEHICLE_NAME.nameBundleObject
                            to "",
                    BundleChoiceVehicle.LAST_VEHICLE_STOP_NAME.nameBundleObject to
                            vehicle.name.substringAfter(" "),
                    "tripId" to vehicle.tripId


                )

                map.findNavController()
                    .navigate(R.id.action_navigation_map_to_detailsFragment, bundle)
 */



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VehicleStopDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VehicleStopDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}