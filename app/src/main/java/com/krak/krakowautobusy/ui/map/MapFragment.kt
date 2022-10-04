package com.krak.krakowautobusy.ui.map

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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.krak.krakowautobusy.R
import com.krak.krakowautobusy.databinding.FragmentMapBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MapFragment : Fragment() {

    private val binding get() = _binding!!

    private var _binding: FragmentMapBinding? = null
    private var showVehiclesOnMap = HowShowVehicles.ALL
    private val viewModel: MapViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View {
        Log.i("MapFragment", "OnCreateView")
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
        setIconOnMapShowAllVehiclesOrFavoriteButton(binding.MapShowAllVehiclesOrFavorite)
        setIconbusStopButtonButton(binding.busStopButton)
        changeColorOfLocationButton(binding.locationfab)

        binding.MapShowAllVehiclesOrFavorite.setOnClickListener{
            viewModel.isFavouritMap()
            setIconOnMapShowAllVehiclesOrFavoriteButton(binding.MapShowAllVehiclesOrFavorite)
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

        binding.locationfab.setOnClickListener{
            viewModel.isSetLocation()
            changeColorOfLocationButton(binding.locationfab)
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

        binding.busStopButton.setOnClickListener {
            viewModel.isShowBusStops()
            setIconbusStopButtonButton(binding.busStopButton)
            it.setBackgroundColor(Color.rgb(224,224,224))
            it.animate()
                .scaleX(1.05f).scaleY(1.05f).setDuration(300).withEndAction {
                    it.animate().scaleX(1.0f).scaleY(1.0f).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

    }

    private fun setIconOnMapShowAllVehiclesOrFavoriteButton(button: ExtendedFloatingActionButton) {
        val colorGray = "#FF757575"
        val colorGreen = "#32CD32"
        if (viewModel.isFavourit.value!!) {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(colorGreen))
        } else {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(colorGray))
        }
    }

    private fun changeColorOfLocationButton(button: ExtendedFloatingActionButton) {
        val colorGray = "#FF757575"
        val colorGreen = "#32CD32"
        if (viewModel.setMyLocation.value!!) {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(colorGreen))
        } else {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(colorGray))
        }
    }

    private fun setIconbusStopButtonButton(button: ExtendedFloatingActionButton) {

        val colorGreen = "#32CD32"
        val iconBus = AppCompatResources
            .getDrawable(requireContext(), R.drawable.bus_icon)!!
        val iconBusStop = AppCompatResources
            .getDrawable(requireContext(), R.drawable.ic_bus_stop)!!


        button.iconTint=ColorStateList.valueOf(Color.parseColor(colorGreen))


        if (viewModel.showBusStops.value!!) {
            button.icon = iconBus
        } else {
            button.icon = iconBusStop
        }

        /*

           val colorGray = "#FF757575"
        val colorGreen = "#32CD32"
        if (viewModel.isFavourit.value!!) {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(colorGreen))
        } else {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(colorGray))
        }

         */
    }

    override fun onStart() {
        super.onStart()
        Log.i("MapFragment", "onStart")
    }

    private fun addControllerToMap() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i("MapFragment", "onDestroyView")
    }
}