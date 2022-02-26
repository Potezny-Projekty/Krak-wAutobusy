package com.example.krakowautobusy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.krakowautobusy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.main_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_map, R.id.navigation_favorite
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        hideAppTitleBar()
xD()
    }

    private var mLastContentHeight = 0
    val keyboardLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val currentContentHeight: Int =
            findViewById<View>(Window.ID_ANDROID_CONTENT).getHeight()
        if (mLastContentHeight > currentContentHeight + 100) {
            Log.e("animacje", "onGlobalLayout: Keyboard is open")
            mLastContentHeight = currentContentHeight
        } else if (currentContentHeight > mLastContentHeight + 100) {
            Log.e("animacje", "close")
            mLastContentHeight = currentContentHeight


            val editTxt=findViewById<EditText>(R.id.search_edit_text)
            if(editTxt !=null){
                editTxt.clearFocus()
            }else{
                Log.e("czy","null")
            }

        }
    }
    fun xD(){


        binding.navView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

    }



    private fun hideAppTitleBar(){
        supportActionBar?.hide()

    }
}