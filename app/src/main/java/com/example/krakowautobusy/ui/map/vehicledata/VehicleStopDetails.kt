package com.example.krakowautobusy.ui.map.vehicledata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.FragmentVehicleStopDetailsBinding
import com.example.krakowautobusy.ui.vehiclestop.AdapterListViewDepatures
import com.example.krakowautobusy.ui.vehiclestop.Bundle_Vehicle_Stop
import java.text.SimpleDateFormat
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
    private lateinit var  adapter:AdapterListViewDepatures

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
                refreshListDepeartures()


                Api.getApi().getBusDepartures(idStopPoint
                ) { response ->
                    Log.e("ojej","Rozmiar:"+response.body()!!.actual.size)
                    //  for(x in response.body()!!.actual){
                    //     Log.e("ojej",x.plannedTime.toString())
                    //  }
                  //  adapter= AdapterListViewDepatures(response.body()!!.actual,requireContext())
                    adapter.changeDataset(response.body()!!.actual)
                    binding.listdetailed.adapter=adapter


                }



                mainHandler.postDelayed(this,TIMER_REFRESH_DEPARTURE_LIST )

            }
        }

        mainHandler.postDelayed(timerRefreshDepartureList,TIMER_REFRESH_DEPARTURE_LIST)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVehicleStopDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fillViewDataFromBundle()
        addDeparturesToListView(requireArguments().getString(Bundle_Vehicle_Stop.ID_STOP_POINT .nameBundle).toString())
       idStopPoint=Bundle_Vehicle_Stop.ID_STOP_POINT .nameBundle
        Log.e("aax",":"+idStopPoint)

        refreshListDepeartures()
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
            adapter= AdapterListViewDepatures(response.body()!!.actual,requireContext())
            adapter.changeDataset(response.body()!!.actual)
           binding.listdetailed.adapter=adapter


        }
    }






    fun fillViewDataFromBundle( ){

        binding.lineNumberTop.text= requireArguments().getString(Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle).toString()


    }





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