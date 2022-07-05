package com.example.krakowautobusy.ui.loadingPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.krakowautobusy.MainActivity
import com.example.krakowautobusy.databinding.FragmentLoadingPageBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoadingPageFragment : AppCompatActivity() {
    private lateinit var binding: FragmentLoadingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

        binding = FragmentLoadingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Executors.newSingleThreadScheduledExecutor().schedule({
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 3, TimeUnit.SECONDS)
        }

}