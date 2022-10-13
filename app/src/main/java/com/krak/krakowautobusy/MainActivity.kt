package com.krak.krakowautobusy

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
import com.krak.krakowautobusy.api.Api
import com.krak.krakowautobusy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private fun setStrictMode() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Api.buildApi(applicationContext)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setStrictMode()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.main_fragment)


        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.navigation_no_internet || nd.id == R.id.navigation_loading_page) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }



        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_map, R.id.navigation_favorite,R.id.navigation_favoritevehiclestop
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        hideAppTitleBar()


        checkConnectWithInternet(navController)

    }



    private fun hideAppTitleBar() {
        supportActionBar?.hide()
    }


    private fun checkConnectWithInternet(
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

            override fun onAvailable(network: Network) {
                super.onAvailable(network)

                runOnUiThread {

                    val currentDestination = navController.currentDestination!!
                    if (currentDestination.id == R.id.navigation_no_internet) {
                        navController.navigate(R.id.action_navigation_no_internet_to_navigation_map)
                    }
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)

                runOnUiThread{
                    navController.navigate(R.id.action_global_navigation_no_internet)
                }
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