package com.krak.krakowautobusy.ui.map.network

import com.krak.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ActualPositionApi {
    @FormUrlEncoded
    @POST("geoserviceDispatcher/services/vehicleinfo/vehicles")
    fun getAllVehicles(@Field("lastUpdate") lastUpdate : Long,
                       @Field("positionType") positionType : String = "CORRECTED",
                       @Field("colorType") colorType : String = "ROUTE_BASED",
                       @Field("language") language : String = "pl") : Call<AllVehicles>

    @FormUrlEncoded
    @POST("geoserviceDispatcher/services/pathinfo/vehicle")
    fun getPathVehicle(@Field("id") id: String): Call<JsonObject>
}