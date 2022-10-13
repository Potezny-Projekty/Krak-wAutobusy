package com.krak.krakowautobusy.ui.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi

import org.osmdroid.views.MapView

class LocationProviderChangedReceiver(private var map: MapView,
                                      private var userLocation: UserLocation
) : BroadcastReceiver() {

    private var isGpsEnabled: Boolean = false
    private var isNetworkEnabled: Boolean = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let { act ->
            if (act.matches("android.location.PROVIDERS_CHANGED".toRegex())) {
                val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)


                if (isGpsEnabled || isNetworkEnabled) {
                    userLocation.getLocationUpdates(map)
                    userLocation.startLocationUpdates()
                }else{
                    if (!isGpsEnabled || !isNetworkEnabled){
                        userLocation.stopLocationUpdates()
                    }
                }
            }
        }
    }
}