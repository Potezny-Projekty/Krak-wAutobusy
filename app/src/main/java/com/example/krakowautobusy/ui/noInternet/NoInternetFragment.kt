package com.example.krakowautobusy.ui.noInternet

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.krakowautobusy.databinding.FragmentNoInternetBinding


class NoInternetFragment : Fragment() {
    private var _binding: FragmentNoInternetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNoInternetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.progressBar.visibility = View.GONE

        binding.refreshButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            Handler(Looper.myLooper()!!).postDelayed({
                // tutaj będzie wywołanie metody sprawdzającej czy mamy już połączenie, jeśli zwróci ona true wracamy do poprzedniego widoku
                // jeśli nie
                binding.progressBar.visibility = View.GONE
            }, 3000)
        }
        return root
    }
}