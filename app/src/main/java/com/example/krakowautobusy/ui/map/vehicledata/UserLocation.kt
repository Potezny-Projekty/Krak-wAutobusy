package com.example.krakowautobusy.ui.map.vehicledata

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
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
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.M)
class UserLocation(var activity: AppCompatActivity) {
    private val PERMISSION_ID = 42
    private var REQUEST_CHECK_CODE: Int = 1

    private var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    var latitude: Double = 50.06173293019267
    var longtitude: Double = 19.937894523426294
    private var enabled = true

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun getLocationUpdates(map: MapView): GeoPoint {
        val locationPoint = GeoPoint(latitude, longtitude)
        if (enabled) {
            if (checkPermissions()) {
                Log.i("UserLocation", "Permission status: " + checkPermissions())
                Log.i("UserLocation", "Location status: " + isLocationEnabled())
                if (isLocationEnabled()) {
                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
                    locationRequest = LocationRequest()
                    locationRequest.interval = 50000
                    locationRequest.fastestInterval = 50000
                    locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
                    locationRequest.priority =
                        LocationRequest.PRIORITY_HIGH_ACCURACY
                    locationCallback = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            Log.i("UserLocation", locationResult.locations.isNotEmpty().toString())
                            super.onLocationResult(locationResult)
                            if (locationResult.locations.isNotEmpty()) {
                                val location =
                                    locationResult.lastLocation
                                latitude = location.latitude
                                longtitude = location.longitude
                                Log.i("UserLocation", "New pos from location${location.latitude}")
                                Log.i("UserLocation", "New pos from location${location.longitude}")
                                updateLocationMarkerPosition(map)
                            }
                        }
                    }
                } else {
                    requestLocation()
                }
            } else {
                requestPermissions()
            }
        }
        return locationPoint
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity.getSystemService(LocationManager::class.java) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestLocation() {
        val builder: LocationSettingsRequest.Builder
        val request = LocationRequest()
            .setFastestInterval(1500)
            .setInterval(3000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        builder = LocationSettingsRequest.Builder()
            .addLocationRequest(request)

        val result: Task<LocationSettingsResponse> =
            LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build())

        result.addOnCompleteListener(object : OnCompleteListener<LocationSettingsResponse> {
            override fun onComplete(task: Task<LocationSettingsResponse>) {
                try {
                    task.getResult(ApiException::class.java)
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            try {
                                val resolvableApiException: ResolvableApiException =
                                    e as ResolvableApiException
                                resolvableApiException.startResolutionForResult(
                                    activity,
                                    REQUEST_CHECK_CODE
                                )
                            } catch (ex: IntentSender.SendIntentException) {
                                ex.printStackTrace()
                            } catch (ex: ClassCastException) {
                                ex.printStackTrace()
                            }
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            Log.i("UserLocation", "Settings change unavailable")
                        }
                    }
                }
            }
        })
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
    fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()!!
        )
    }
}