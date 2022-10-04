package com.krak.krakowautobusy.networkttss

import com.krak.krakowautobusy.database.VehicleType
import com.krak.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.google.gson.JsonObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PositionVehicle:PositionVehicleInterface {

    private val tramHelperInstance = getInstanceRetrofit(VehicleType.TRAM).create<ActualPositionRetrofitApi>()

    private val busHelperInstance = getInstanceRetrofit(VehicleType.BUS).create<ActualPositionRetrofitApi>()


    private  fun getInstanceRetrofit(busOrTram:VehicleType):Retrofit{
        return Retrofit.Builder().baseUrl(busOrTram.baseUrlTtss)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }





    private fun generateHelperPosition(lastUpdate: Long, retrofitAp:ActualPositionRetrofitApi, callbackResponse: (Response<AllVehicles>) -> Unit){
        return retrofitAp.getAllVehicleFromUrl(lastUpdate).enqueue(object:
            Callback<AllVehicles> {
            override fun onResponse(call: Call<AllVehicles>, response: Response<AllVehicles>) {
                callbackResponse(response)
            }

            override fun onFailure(call: Call<AllVehicles>, t: Throwable) {

            }
        })
    }


    private fun generateHelperPath(idVehicle: String, retrofitAp:ActualPositionRetrofitApi, callbackResponse: (Response<JsonObject>) -> Unit){
        return retrofitAp.getPathVehicleFromUrl(idVehicle).enqueue(object:
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                callbackResponse(response)
            }



            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }
        })
    }



    override fun getBusPosition(
        lastUpdate:Long,
        callbackResponse: (Response<AllVehicles>) -> Unit
    ): Unit {

        return generateHelperPosition(lastUpdate,busHelperInstance,callbackResponse)
    }

    override fun getAllVehiclePosition() {
        TODO("Not yet implemented")
    }

    override fun getTramPosition(
        lastUpdate:Long,
        callbackResponse: (Response<AllVehicles>) -> Unit
    ): Unit {

         return generateHelperPosition(lastUpdate,tramHelperInstance,callbackResponse)
    }

    override fun getTramPath(
        idVehicle: String,
        callbackResponse: (Response<JsonObject>) -> Unit
    ) {
        return generateHelperPath(idVehicle,tramHelperInstance,callbackResponse)
    }

    override fun getBusPath(
        idVehicle: String,
        callbackResponse: (Response<JsonObject>) -> Unit
    ) {
        return generateHelperPath(idVehicle,busHelperInstance,callbackResponse)
    }
}