package com.example.krakowautobusy.ui.map.network

import com.example.krakowautobusy.ui.map.network.requestData.PathVehicleData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PathVehicleApi {
    @POST("geoserviceDispatcher/services/vehicleinfo/vehicles")
    fun getPathVehicle(@Body pathVehicleData: PathVehicleData
    ) : Call<String>
}