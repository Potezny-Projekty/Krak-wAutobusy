package com.example.krakowautobusy.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.databinding.FragmentDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


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

    override fun onStart(){
        val navigationView = requireActivity()
        var bottomNavBar = navigationView.findViewById<BottomNavigationView>(com.example.krakowautobusy.R.id.nav_view)

        bottomNavBar.menu.setGroupCheckable(0,false,true)
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        val navigationView = requireActivity()
        var bottomNavBar = navigationView.findViewById<BottomNavigationView>(com.example.krakowautobusy.R.id.nav_view)

        bottomNavBar.menu.setGroupCheckable(0,true,true)
    }

}