package com.krak.krakowautobusy.networkttss

import com.krak.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.Call
import retrofit2.http.*

interface TimeTableRetrofitApi {
    ///internetservice/services/tripInfo/tripPassages


    @GET("services/tripInfo/tripPassages")
    fun getTimeTableVehicles(@Query("tripId") lastUpdate : String,
                             @Query("vehicleId") positionType : String) : Call<TimeTableData>
}