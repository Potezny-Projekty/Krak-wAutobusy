package com.example.krakowautobusy

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer


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
                R.id.navigation_map, R.id.navigation_favorite,R.id.navigation_favoritevehiclestop
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        hideAppTitleBar()
        val api= Api.getApi()

        for(x in api.getInfoAboutLinePatternNumber(5)){
                 Log.e("testbaza",x.firstStopName.  toString()+"/"+x.lastStopName+"/"+x.numberLine)
               }
        checkConnectWithInternet(navController)

    }



    private fun hideAppTitleBar() {
        supportActionBar?.hide()
    }


    fun checkConnectWithInternet(
        navController: NavController
    ) {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.i("INTERNET", "onAvailable")
                runOnUiThread {
                    Log.i("DESTINATION", navController.currentDestination.toString())
                    val currentDestination = navController.currentDestination!!
                    if (currentDestination.id == R.id.navigation_no_internet) {
                        navController.navigate(R.id.action_navigation_no_internet_to_navigation_map)
                    }
                }
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                Log.i("INTERNET", "onLost")
                runOnUiThread{
                    navController.navigate(R.id.action_global_navigation_no_internet)
                }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                Log.i("INTERNET", "onUnavailable")
            }

        }

        if (!verifyAvailableNetwork(connectivityManager)) {
            navController.navigate(R.id.action_global_navigation_no_internet)
        }
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }


    private fun verifyAvailableNetwork(connectivityManager : ConnectivityManager):Boolean{
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

}