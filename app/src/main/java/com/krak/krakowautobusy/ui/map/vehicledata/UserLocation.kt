package com.krak.krakowautobusy.ui.map.vehicledata

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
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
import com.google.android.gms.tasks.Task
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


private const val TAG = "UserLocation"

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.M)
class UserLocation(var activity: AppCompatActivity){
    private val permisionID = 42
    private var requestCheckCode: Int = 1

    private val locationInterval: Long = 1500// in ms
    private val locationFastenInterval: Long = 3000 // in ms
    private val smallestDisplacement = 5.0F // 1 - 1meter
    private val priority = LocationRequest.PRIORITY_HIGH_ACCURACY

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null

    var latitude: Double = 50.06173293019267
    var longtitude: Double = 19.937894523426294

    var locationMarker : Marker? = null


    fun getLocationUpdates(map: MapView) {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(activity)
                locationRequest = LocationRequest()
                locationRequest!!.interval = locationInterval
                locationRequest!!.fastestInterval = locationFastenInterval
                locationRequest!!.smallestDisplacement = smallestDisplacement
                locationRequest!!.priority = priority
                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        if (locationResult.locations.isNotEmpty()) {
                            val location =
                                locationResult.lastLocation
                            latitude = location.latitude
                            longtitude = location.longitude
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
            .setFastestInterval(locationFastenInterval)
            .setInterval(locationInterval)
            .setPriority(priority)

        builder = LocationSettingsRequest.Builder()
            .addLocationRequest(request)

        val result: Task<LocationSettingsResponse> =
            LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
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
                                requestCheckCode
                            )
                        } catch (ex: IntentSender.SendIntentException) {
                            ex.printStackTrace()
                        } catch (ex: ClassCastException) {
                            ex.printStackTrace()
                        }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        Log.i(TAG, "Settings change unavailable")
                    }
                }
            }
        }
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
            permisionID
        )
    }

    fun createLocationMarker(map: MapView, locationMarkerDrawable: Drawable) {
        val marker = Marker(map)
        val locationPoint = GeoPoint(latitude, longtitude)
        marker.icon = locationMarkerDrawable
        marker.title = "Twoja pozycja"
        marker.id = "location"
        marker.position = locationPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        locationMarker = marker
    }

    fun updateLocationMarkerPosition(map: MapView) {

        val locationPoint = GeoPoint(latitude, longtitude)
        for ((index) in map.overlays.withIndex()) {
            if (map.overlays[index] is Marker && (map.overlays[index] as Marker).id == "location") {
                if ((map.overlays[index] as Marker).position == locationPoint) {

                } else {
                    (map.overlays[index] as Marker).position = locationPoint
                    map.controller.setCenter(locationPoint)
                    map.invalidate()
                }
            }
        }
    }

    fun startLocationUpdates() {
        fusedLocationProviderClient?.requestLocationUpdates(
            locationRequest!!,
            locationCallback!!,
            Looper.myLooper()!!
        )
    }

    fun stopLocationUpdates() {
        fusedLocationProviderClient?.removeLocationUpdates(locationCallback!!)
    }
}