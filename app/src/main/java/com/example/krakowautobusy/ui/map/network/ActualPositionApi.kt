package com.example.krakowautobusy.ui.map.network

import com.example.krakowautobusy.ui.map.network.requestData.ActualPositionData
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ActualPositionApi {
    @POST("geoserviceDispatcher/services/vehicleinfo/vehicles")
    fun getAllVehicles(@Body actualPositionData:
                       ActualPositionData) : Call<AllVehicles>
}