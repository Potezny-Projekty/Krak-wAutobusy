package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.database.VehicleType
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import com.google.gson.JsonObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TimeTableVehicle :TimeTableVehicleInterface {
    private val busHelperInstance = getInstanceRetrofit(VehicleType.BUS).create<TimeTableRetrofitApi>()

    private  fun getInstanceRetrofit(busOrTram:VehicleType): Retrofit {
        return Retrofit.Builder().baseUrl(busOrTram.baseUrlTtss)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getBusVehicleTimeTable(
        idVehicle: String,
        idTrip: String,
        callbackResponse: (Response<TimeTableData>) -> Unit
    ) {
        return generateGetBusVehicleTimeTable(idVehicle,idTrip,busHelperInstance,callbackResponse)
    }






    private fun generateGetBusVehicleTimeTable(idVehicle: String,idTrip:String, retrofitAp:TimeTableRetrofitApi, callbackResponse: (Response<TimeTableData>) -> Unit){
        return retrofitAp.getTimeTableVehicles(idTrip,idVehicle).enqueue(object:
            Callback<TimeTableData> {
            override fun onResponse(call: Call<TimeTableData>, response: Response<TimeTableData>) {
                callbackResponse(response)
            }



            override fun onFailure(call: Call<TimeTableData>, t: Throwable) {

            }
        })
    }
}