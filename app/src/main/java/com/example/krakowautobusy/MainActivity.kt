package com.example.krakowautobusy

import android.os.Bundle
import android.util.Log
import android.view.View

import android.view.ViewTreeObserver
import android.view.Window
import android.widget.EditText


import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.krakowautobusy.databinding.ActivityMainBinding

import com.example.krakowautobusy.database.Database
import com.example.krakowautobusy.database.DatabaseInterface
import com.example.krakowautobusy.ui.SearchViewFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.main_fragment)


        //removes navBar from noInternetFragment and loadingPageFragment
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.navigation_no_internet || nd.id == R.id.navigation_loading_page) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }


        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_map, R.id.navigation_favorite
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        hideAppTitleBar()


        val databaseInterface = DatabaseInterface(Database.getInstance(this))

//        Log.i("MainActivity",databaseInterface.addLine("TEST1","TEST2",20).toString())
//        Log.i("MainActivity",databaseInterface.getLine("1"))
//        Log.i("MainActivity",databaseInterface.updateLine(1,"TEST1","TEST2",20).toString())
//        Log.i("MainActivity",databaseInterface.addLine("TEST1XDDD","TEST2XDDD",20).toString())
//        Log.i("MainActivity",databaseInterface.deleteLine(2).toString())

//xD()
    }




    }
/*
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

*/

    private fun hideAppTitleBar(){
        supportActionBar?.hide()



       // val databaseInterface = DatabaseInterface(Database.getInstance(this))

      //  Log.i("MainActivity", databaseInterface.addLine("Przystanek TEST1", "Przystanek TEST2", 20).toString())
      //  Log.i("MainActivity", databaseInterface.addBusStop().toString())

    }


}