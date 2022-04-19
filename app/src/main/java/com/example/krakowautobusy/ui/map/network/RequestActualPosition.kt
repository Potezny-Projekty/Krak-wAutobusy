package com.example.krakowautobusy.ui.map.network

import android.util.Log
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RequestActualPosition : Callback<AllVehicles> {
    override fun onFailure(call: Call<AllVehicles>, t: Throwable) {
        Log.d("ayush", t.toString())

    }

    override fun onResponse(call: Call<AllVehicles>, response: Response<AllVehicles>) {
        Log.d("ayush", response.body().toString())
    }
}