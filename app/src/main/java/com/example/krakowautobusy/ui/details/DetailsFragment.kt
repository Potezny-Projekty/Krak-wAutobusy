package com.example.krakowautobusy.ui.details

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.FragmentDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.osmdroid.views.MapView


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        uncheckedAllNavMenuOption()
    }

    fun uncheckedAllNavMenuOption() {
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.menu.setGroupCheckable(0, true, false)
        for (i in 0 until navView.menu.size()) {
            navView.menu.getItem(i).isChecked = false
        }
        navView.menu.setGroupCheckable(0, true, true)
    }
}