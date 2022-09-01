package com.example.krakowautobusy.ui.map

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
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
        val colorGray = "#FF757575"
        val colorGreen = "#32CD32"

        binding.MapShowAllVehiclesOrFavorite.setOnClickListener{
            viewModel.isFavouritMap()
            if (mapViewModel.isFavourit.value!!) {
                binding.MapShowAllVehiclesOrFavorite.iconTint =
                    ColorStateList.valueOf(Color.parseColor(colorGreen))
            } else {
                binding.MapShowAllVehiclesOrFavorite.iconTint =
                    ColorStateList.valueOf(Color.parseColor(colorGray))
            }
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

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

        binding.busStopButton.setOnClickListener {
            mapViewModel.isShowBusStops()
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
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