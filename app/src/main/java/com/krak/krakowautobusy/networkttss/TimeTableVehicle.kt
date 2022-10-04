package com.krak.krakowautobusy.networkttss

import com.krak.krakowautobusy.database.VehicleType
import com.krak.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TimeTableVehicle :TimeTableVehicleInterface {
    private val busHelperInstance = getInstanceRetrofit(VehicleType.BUS).create<TimeTableRetrofitApi>()
    private val tramHelperInstance = getInstanceRetrofit(VehicleType.TRAM).create<TimeTableRetrofitApi>()
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


    override fun getTramVehicleTimeTable(
        idVehicle: String,
        idTrip: String,
        callbackResponse: (Response<TimeTableData>) -> Unit
    ) {
        return generateGetTramVehicleTimeTable(idVehicle,idTrip,tramHelperInstance,callbackResponse)
    }


    private fun generateGetBusVehicleTimeTable(idVehicle: String,idTrip:String, retrofitAp:TimeTableRetrofitApi, callbackResponse: (Response<TimeTableData>) -> Unit){
        return retrofitAp.getTimeTableVehicles(idTrip,idVehicle).enqueue(object:
            Callback<TimeTableData> {
            override fun onResponse(call: Call<TimeTableData>, response: Response<TimeTableData>) {
                if(response.body()!=null && response.isSuccessful) {
                    callbackResponse(response)
                }
            }



            override fun onFailure(call: Call<TimeTableData>, t: Throwable) {

            }
        })
    }



    private fun generateGetTramVehicleTimeTable(idVehicle: String,idTrip:String, retrofitAp:TimeTableRetrofitApi, callbackResponse: (Response<TimeTableData>) -> Unit){
        return retrofitAp.getTimeTableVehicles(idTrip,idVehicle).enqueue(object:
            Callback<TimeTableData> {
            override fun onResponse(call: Call<TimeTableData>, response: Response<TimeTableData>) {

                //if(response!=null && response.isSuccessful){

                if(response.body()!=null && response.isSuccessful) {
                    callbackResponse(response)
                }

                //}


            }



            override fun onFailure(call: Call<TimeTableData>, t: Throwable) {

            }
        })
    }
}