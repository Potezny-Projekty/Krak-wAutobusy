package com.example.krakowautobusy

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.VehicleStop
import com.example.krakowautobusy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    fun show() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        // w listBusMasz
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Api.buildApi(applicationContext)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        show()

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





        val api= Api.getApi()


     //   api.addLineToFavourite(137)
     //   api.addLineToFavourite(537)
     //   Log.e("testbaza",api.isLineFavourite(137).toString())
     //   Log.e("testbaza",api.isLineFavourite(537).toString())

     //   for(x in api.getAllFavouriteLine()){
       //     Log.e("testbaza",x.firstStopName.  toString()+"/"+x.lastStopName+"/"+x.numberLine)
     //   }

       // api.removeLinesFromFavourites(537)
     //   api.removeLinesFromFavourites(137)

      //  Log.e("testbaza",api.isLineFavourite(137).toString())
      //  Log.e("testbaza",api.isLineFavourite(537).toString())

       // Log.e("testbaza",api.removeLineFromFavourite(2).toString())
      //  Log.e("testbaza",api.isLineFavouriteName(137).toString()+".....................................")
      //  api.removeLinesFromFavouritesName(137)
     //   Log.e("testbaza",api.isLineFavouriteName(137).toString()+".....................................")
      //  Log.e("testbaza",api.isLineFavourite(2).toString())


        Log.e("testbaza",api.getInfoAboutLineConcretDirectionLastStopName(537,"Witkowice").numberLine.toString())
        Log.e("testbaza",api.getInfoAboutLineConcretDirectionLastStopName(537,"Witkowice").firstStopName.toString())
        Log.e("testbaza",api.getInfoAboutLineConcretDirectionLastStopName(537,"Witkowice").lastStopName.toString())
        Log.e("testbaza",api.getInfoAboutLineConcretDirectionLastStopName(537,"Witkowice").isFavourite. toString())
        Log.e("testbaza",api.getInfoAboutLineConcretDirectionLastStopName(537,"Witkowice").numberLine.toString())
      // Log.e("testbaza",api.getInfoAboutLineConcretDirectionLastStopName(537,"Witkowice Nowe").numberLine.toString())

        for(x in api.getInfoAboutLinePatternNumber(5)){
                 Log.e("testbaza",x.firstStopName.  toString()+"/"+x.lastStopName+"/"+x.numberLine)
               }

    }



    private fun hideAppTitleBar() {
        supportActionBar?.hide()




    }


}