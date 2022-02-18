package com.example.krakowautobusy.ui.map

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.krakowautobusy.databinding.FragmentMapBinding
import com.google.android.gms.maps.SupportMapFragment

import com.example.krakowautobusy.R


class MapFragment : Fragment() {

    enum class HowShowVehicles {
        ALL,FAVORITE
    }


    private lateinit var mapViewModel: MapViewModel
    private var _binding: FragmentMapBinding? = null




    private var showVehiclesOnMap=HowShowVehicles.ALL






    private fun showToast(message:String){

        val x_offset_position= resources.getInteger(R.integer.toast_x_offset_position)
        val y_offset_position= resources.getInteger(R.integer.toast_y_offset_position)
        val myInflater = LayoutInflater.from(context)
        val view = myInflater!!.inflate(R.layout.tost_show_allvehivles_orone, null)
        val toastText=view.findViewById(R.id.Tost_text) as TextView
        toastText.text=message
        val showOnMapOneOrAllVehiclesToast = Toast(context)
        showOnMapOneOrAllVehiclesToast.view = view
        showOnMapOneOrAllVehiclesToast.duration = Toast.LENGTH_LONG
        showOnMapOneOrAllVehiclesToast.setGravity(Gravity.BOTTOM or Gravity.CENTER, x_offset_position, y_offset_position)
        showOnMapOneOrAllVehiclesToast.show()
    }
    private fun addCallbackClickShowAllOrOneVehicles(){
        binding.MapShowAllVehiclesOrFavorite.setOnClickListener {
            showVehiclesOnMap = if(showVehiclesOnMap==HowShowVehicles.ALL){
                showToast(getString(R.string.show_favorite_vehicles))
                HowShowVehicles.FAVORITE
            }else{
                showToast(getString(R.string.show_all_vehicles))
                HowShowVehicles.ALL
            }

        }
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mapViewModel =
            ViewModelProvider(this).get(MapViewModel::class.java)

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        mapViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })




        addControllerToMap()
        addCallbackClickShowAllOrOneVehicles()

        return root
    }


    override fun onStart() {
        super.onStart()

    }


    private fun addControllerToMap(){
        val mapFragment=  childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(MapsController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}