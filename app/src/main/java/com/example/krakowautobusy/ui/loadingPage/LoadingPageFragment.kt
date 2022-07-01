package com.example.krakowautobusy.ui.loadingPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.FragmentLoadingPageBinding

class LoadingPageFragment : Fragment() {
    private lateinit var binding: FragmentLoadingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoadingPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }
}