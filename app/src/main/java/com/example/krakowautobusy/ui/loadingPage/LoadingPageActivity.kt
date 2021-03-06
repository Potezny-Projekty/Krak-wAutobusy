package com.example.krakowautobusy.ui.loadingPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.krakowautobusy.MainActivity
import com.example.krakowautobusy.R
import com.example.krakowautobusy.databinding.ActivityLoadingPageBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoadingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

        binding = ActivityLoadingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun hideAppTitleBar() {
        supportActionBar?.hide()




    }

    override fun onStart() {
        super.onStart()
        hideAppTitleBar()
        Executors.newSingleThreadScheduledExecutor().schedule({
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            startActivity(intent)
        }, 3, TimeUnit.SECONDS)
        }

}