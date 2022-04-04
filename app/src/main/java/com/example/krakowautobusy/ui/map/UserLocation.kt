package com.example.krakowautobusy.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.M)
class UserLocation(var activity: AppCompatActivity) {
    val PERMISSION_ID = 42
    var mFusedLocationClient: FusedLocationProviderClient

    init {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }
    fun getLastLocation() {
        Log.i("XD", "permission status: " + checkPermissions())
        if (checkPermissions()) {
            Log.i("XD", "permission status: " + checkPermissions())
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        Log.i("XD", location.latitude.toString())
                        Log.i("XD", location.longitude.toString())
                    }
                }
            } else {
                Toast.makeText(activity, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                activity.startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()!!
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            Log.i("XD", mLastLocation.longitude.toString())
            Log.i("XD", mLastLocation.latitude.toString())
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity.getSystemService(LocationManager::class.java) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSION_ID) {
//            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                getLastLocation()
//            }
//        }
//    }

}