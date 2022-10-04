package com.krak.krakowautobusy.ui.noInternet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krak.krakowautobusy.databinding.FragmentNoInternetBinding


class NoInternetFragment : Fragment() {
    private lateinit var _binding: FragmentNoInternetBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNoInternetBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
}