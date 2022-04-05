package com.example.krakowautobusy.ui.map.vehicledata

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


@Suppress("DEPRECATION")
@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.M)
class UserLocation(var activity: AppCompatActivity) {
    private val PERMISSION_ID = 42
    private var mFusedLocationClient: FusedLocationProviderClient
    var latitude: Double = 50.06173293019267
    var longtitude: Double = 19.937894523426294

    init {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun getLastLocation(map: MapView): GeoPoint {
        val locationPoint = GeoPoint(latitude, longtitude)
        if (checkPermissions()) {
            Log.i("UserLocation", "Permission status: " + checkPermissions())
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        latitude = location.latitude
                        longtitude = location.longitude
                        Log.i(
                            "UserLocation",
                            "Position from getLastLocation() " + location.latitude.toString()
                        )
                        Log.i(
                            "UserLocation",
                            "Position from getLastLocation() " + location.longitude.toString()
                        )
                        updateLocationMarkerPosition(map)
                    }
                }
            }
        } else {
            requestPermissions()
        }
        return locationPoint
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
            Log.i(
                "UserLocation",
                "Location from mLocationCallbacak" + mLastLocation.latitude.toString()
            )
            Log.i(
                "UserLocation",
                "Location from mLocationCallbacak" + mLastLocation.longitude.toString()
            )
            latitude = mLastLocation.latitude
            longtitude = mLastLocation.longitude
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

    fun drawLocationMarker(map: MapView, busIcon: Drawable) {
        val marker = Marker(map)
        val locationPoint = GeoPoint(latitude, longtitude)

        Log.i("UserLocation", "Drawing initial marker, pos: $latitude, $longtitude")

        marker.icon = busIcon
        marker.title = "Twoja pozycja"
        marker.id = "location"
        marker.position = locationPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)
        map.invalidate()
    }

    fun updateLocationMarkerPosition(map: MapView) {
        Log.i("UserLocation", "Position update Called")
        val locationPoint = GeoPoint(latitude, longtitude)
        for ((index) in map.overlays.withIndex()) {
            if (map.overlays[index] is Marker && (map.overlays[index] as Marker).id == "location") {
                if ((map.overlays[index] as Marker).position == locationPoint) {
                    Log.i("UserLocation", "Position hasnt changed since last update")
                } else {
                    (map.overlays[index] as Marker).position = locationPoint
                    map.controller.setCenter(locationPoint)
                    map.invalidate()
                    Log.i(
                        "UserLocation",
                        "Updated marker position: " + (map.overlays[index] as Marker).position.toString()
                    )
                }
            }
        }
    }
}