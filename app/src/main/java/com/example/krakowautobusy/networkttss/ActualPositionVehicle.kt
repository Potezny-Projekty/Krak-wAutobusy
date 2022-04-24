package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.database.VehicleType
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ActualPositionVehicle:ActualPositionVehicleInterface {

    private val tramHelperInstance = getInstanceRetrofit(VehicleType.TRAM).create<ActualPositionRetrofitApi>()

    private val busHelperInstance = getInstanceRetrofit(VehicleType.BUS).create<ActualPositionRetrofitApi>()


    private  fun getInstanceRetrofit(busOrTram:VehicleType):Retrofit{
        return Retrofit.Builder().baseUrl(busOrTram.baseUrlTtss)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }





    private fun generateHelper(lastUpdate: Long,retrofitAp:ActualPositionRetrofitApi, callbackResponse: (Response<AllVehicles>) -> Unit){
        return retrofitAp.getAllVehicleFromUrl(lastUpdate).enqueue(object:
            Callback<AllVehicles> {
            override fun onResponse(call: Call<AllVehicles>, response: Response<AllVehicles>) {
                callbackResponse(response)
            }

            override fun onFailure(call: Call<AllVehicles>, t: Throwable) {

            }
        })
    }


    override fun getBusPosition(
        lastUpdate:Long,
        callbackResponse: (Response<AllVehicles>) -> Unit
    ): Unit {

        return generateHelper(lastUpdate,busHelperInstance,callbackResponse)
    }

    override fun getAllVehiclePosition() {
        TODO("Not yet implemented")
    }

    override fun getTramPosition(
        lastUpdate:Long,
        callbackResponse: (Response<AllVehicles>) -> Unit
    ): Unit {

        return   return generateHelper(lastUpdate,tramHelperInstance,callbackResponse)
    }
}