package com.krak.krakowautobusy.ui.map

import android.content.res.ColorStateList
import android.content.res.Resources
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
    private val viewModel: MapViewModel by activityViewModels()

    private val greenColor="#32cd32"
    private val grayColor="#757575"
    private val littleGray="#e0e0e0"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMapBinding.inflate(inflater, container, false)

        val root: View = binding.root

        addCallbackClickShowAllOrOneVehicles()

        return root
    }



    private fun addCallbackClickShowAllOrOneVehicles() {
        setIconOnMapShowAllVehiclesOrFavoriteButton(binding.MapShowAllVehiclesOrFavorite)
        setIconbusStopButtonButton(binding.busStopButton)
        changeColorOfLocationButton(binding.locationfab)

        val scaleFactorUp=1.05f
        val scaleFactorNormal=1f
        val animDurationMS=300L

        binding.MapShowAllVehiclesOrFavorite.setOnClickListener{
            viewModel.isFavouritMap()
            setIconOnMapShowAllVehiclesOrFavoriteButton(binding.MapShowAllVehiclesOrFavorite)
            it.setBackgroundColor( Color.parseColor(littleGray))
            it.animate()
                .scaleX(scaleFactorUp).scaleY(scaleFactorUp).setDuration(animDurationMS).withEndAction {
                    it.animate().scaleX(scaleFactorNormal).scaleY(scaleFactorNormal).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

        binding.locationfab.setOnClickListener{
            viewModel.isSetLocation()
            changeColorOfLocationButton(binding.locationfab)
            it.setBackgroundColor(Color.parseColor(littleGray))
            it.animate()
                .scaleX(scaleFactorUp).scaleY(scaleFactorUp).setDuration(animDurationMS).withEndAction {
                    it.animate().scaleX(scaleFactorNormal).scaleY(scaleFactorNormal).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

        binding.busStopButton.setOnClickListener {
            viewModel.isShowBusStops()
            setIconbusStopButtonButton(binding.busStopButton)
            it.setBackgroundColor(Color.parseColor(littleGray))
            it.animate()
                .scaleX(scaleFactorUp).scaleY(scaleFactorUp).setDuration(animDurationMS).withEndAction {
                    it.animate().scaleX(scaleFactorNormal).scaleY(scaleFactorNormal).start()
                    it.setBackgroundColor(Color.WHITE)
                }.start()
        }

    }

    private fun setIconOnMapShowAllVehiclesOrFavoriteButton(button: ExtendedFloatingActionButton) {
        if (viewModel.isFavourit.value!!) {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(greenColor))
        } else {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(grayColor))
        }
    }

    private fun changeColorOfLocationButton(button: ExtendedFloatingActionButton) {
        if (viewModel.setMyLocation.value!!) {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(greenColor))
        } else {
            button.iconTint =
                ColorStateList.valueOf(Color.parseColor(grayColor))
        }
    }

    private fun setIconbusStopButtonButton(button: ExtendedFloatingActionButton) {


        val iconBus = AppCompatResources
            .getDrawable(requireContext(), R.drawable.bus_icon)!!
        val iconBusStop = AppCompatResources
            .getDrawable(requireContext(), R.drawable.ic_bus_stop)!!

        button.iconTint=ColorStateList.valueOf(Color.parseColor(greenColor))

        if (viewModel.showBusStops.value!!) {
            button.icon = iconBus
        } else {
            button.icon = iconBusStop
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}