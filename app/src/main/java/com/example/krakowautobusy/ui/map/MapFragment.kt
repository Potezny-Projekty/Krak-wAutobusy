package com.example.krakowautobusy.ui.map

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.krakowautobusy.BuildConfig
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.FragmentMapBinding
import com.example.krakowautobusy.databinding.MapActivityBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

class MapFragment : Fragment() {

    private val binding get() = _binding!!

    private lateinit var mapViewModel: MapViewModel
    private var _binding: FragmentMapBinding? = null
    private var showVehiclesOnMap = HowShowVehicles.ALL
    private val viewModel: MapViewModel by viewModels()

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


        addControllerToMap()
        addCallbackClickShowAllOrOneVehicles()

        return root
    }

    enum class HowShowVehicles {
        ALL, FAVORITE
    }

    private fun showToast(message: String) {
        val x_offset_position = resources.getInteger(R.integer.toast_x_offset_position)
        val y_offset_position = resources.getInteger(R.integer.toast_y_offset_position)
        val myInflater = LayoutInflater.from(context)
        val view = myInflater!!.inflate(R.layout.tost_show_allvehivles_orone, null)
        val toastText = view.findViewById(R.id.Tost_text) as TextView
        toastText.text = message
        val showOnMapOneOrAllVehiclesToast = Toast(context)
        showOnMapOneOrAllVehiclesToast.view = view
        showOnMapOneOrAllVehiclesToast.duration = Toast.LENGTH_LONG
        showOnMapOneOrAllVehiclesToast.setGravity(
            Gravity.BOTTOM or Gravity.CENTER,
            x_offset_position,
            y_offset_position
        )
        showOnMapOneOrAllVehiclesToast.show()
    }

    private fun addCallbackClickShowAllOrOneVehicles() {
//        binding.MapShowAllVehiclesOrFavorite.setOnClickListener {
//            showVehiclesOnMap = if (showVehiclesOnMap == HowShowVehicles.ALL) {
//                showToast(getString(R.string.show_favorite_vehicles))
//                HowShowVehicles.FAVORITE
//            } else {
//                showToast(getString(R.string.show_all_vehicles))
//                HowShowVehicles.ALL
//            }
//        }
        binding.MapShowAllVehiclesOrFavorite.setOnClickListener { view: View ->
            //view.findNavController().navigate(R.id.action_navigation_map_to_detailsFragment)
            viewModel.isFavouritMap()
        }
        binding.extendedFab2.setOnClickListener { view: View ->
            viewModel.isSetLocation()
        }


    }

    override fun onStart() {
        super.onStart()
    }

    private fun addControllerToMap() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}