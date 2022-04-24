package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
interface ActualPositionRetrofitApi {

    @FormUrlEncoded
    @POST("geoserviceDispatcher/services/vehicleinfo/vehicles")
    fun getAllVehicleFromUrl(@Field("lastUpdate") lastUpdate : Long,
                             @Field("positionType") positionType : String = "CORRECTED",
                             @Field("colorType") colorType : String = "ROUTE_BASED",
                             @Field("language") language : String = "pl") : Call<AllVehicles>

    @FormUrlEncoded
    @POST("geoserviceDispatcher/services/pathinfo/vehicle")
    fun getPathVehicleFromUrl(@Field("id") id: String): Call<JsonObject>
}