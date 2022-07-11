package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TimeTableRetrofitApi {
    ///internetservice/services/tripInfo/tripPassages

    @FormUrlEncoded
    @POST("internetservice/services/tripInfo/tripPassages")
    fun getTimeTableVehicles(@Field("tripId") lastUpdate : String,
                             @Field("vehicleId") positionType : String) : Call<TimeTableData>
}