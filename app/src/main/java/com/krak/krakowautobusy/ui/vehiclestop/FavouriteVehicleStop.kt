package com.krak.krakowautobusy.ui.vehiclestop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.database.VehicleStopData
import com.krak.krakowautobusy.databinding.FragmentFavouriteVehicleStopBinding
import com.krak.krakowautobusy.ui.map.vehicledata.AdapterListSearchVehicleStop
import com.krak.krakowautobusy.ui.map.vehicledata.SearchViewVehicleStop

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteVehicleStop.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteVehicleStop : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding get() = _binding!!


    private var _binding: FragmentFavouriteVehicleStopBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

       // KURWA()
    }

    override fun onStart() {
        super.onStart()



        if(Api.getApi().getAllFavouriteVehicleStop().size==0) {
            binding.vehiclesnolinefavourite.visibility=View.VISIBLE
        }else{
            binding.vehiclesnolinefavourite.visibility=View.GONE
        }




        KURWA()
    }


    fun addOnClickListenerToVehicleStop(){
        binding.favouriteVehicleStopList.setOnItemClickListener { parent, view, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as VehicleStopData
            Log.e("ojej",""+selectedItem.idStopPoint+'/'+selectedItem.idShort)


            val bundle = bundleOf(
                Bundle_Vehicle_Stop.ID_VEHICLE_STOP.nameBundle to
                        selectedItem.idVehicleStop.toString(),
               Bundle_Vehicle_Stop.NAME_VEHICLE_STOP.nameBundle to
                       selectedItem.name,
                Bundle_Vehicle_Stop.ID_STOP_POINT.nameBundle to
                        selectedItem.idStopPoint.toString()

            )
                                                                                                            //bundle
            Navigation.findNavController(view).navigate(R.id.action_navigate_to_details_vehiclestop,bundle);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteVehicleStopBinding.inflate(inflater, container, false)
        KURWA()
        addAdapterToListFavouriteVehicleStop()
        addOnClickListenerToVehicleStop()
        val root: View = binding.root

        binding.textFavourite.paint?.isUnderlineText = true

        return root
    }



    fun addAdapterToListFavouriteVehicleStop(){

        var adapter=AdapterListSearchVehicleStop(Api.getApi().getAllFavouriteVehicleStop(),requireContext())
        adapter.setRefresh {    Log.e("kurwap","JEGO MAĆ2")
            setDatasetAdapter() }
        binding.favouriteVehicleStopList.adapter =adapter
        binding.favouriteVehicleStopList.deferNotifyDataSetChanged()


    }

    fun KURWA(){
        var xx= childFragmentManager.findFragmentById(R.id.detailFragment) as SearchViewVehicleStop
        xx.setRefreshFun {
            Log.e("kurwap","JEGO MAĆ2")
            setDatasetAdapter()
        }


        xx.setActionWhenHideKeyboard {

            var x=Api.getApi().getAllLine()
            var xx=x.filter( { s -> s.isFavourite }
            )
            if(xx.size!=0){
                binding.vehiclesnolinefavourite.visibility=View.VISIBLE
            }

        }


        xx.setActionWhenShowKeyboard {

            var x=Api.getApi().getAllLine()
            var xx=x.filter( { s -> s.isFavourite }
            )
            if(xx.size==0){
                binding.vehiclesnolinefavourite.visibility=View.GONE
            }}


    }


    fun setDatasetAdapter(){
        Log.e("kurwap","JEGO MAĆ2"+Api.getApi().getAllFavouriteVehicleStop().size)
        (binding.favouriteVehicleStopList.adapter as AdapterListSearchVehicleStop).changeDataset(Api.getApi().getAllFavouriteVehicleStop() as ArrayList<VehicleStopData>)
        (binding.favouriteVehicleStopList.adapter as AdapterListSearchVehicleStop).notifyDataSetChanged()


        if(Api.getApi().getAllFavouriteVehicleStop().size==0) {
            binding.vehiclesnolinefavourite.visibility=View.VISIBLE
        }else{
            binding.vehiclesnolinefavourite.visibility=View.GONE
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouriteVehicleStop.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteVehicleStop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}