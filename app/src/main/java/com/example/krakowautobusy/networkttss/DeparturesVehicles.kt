package com.example.krakowautobusy.networkttss

import android.util.Log
import com.example.krakowautobusy.database.VehicleType
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DeparturesVehicles:DeparturesVehicleInterface {

    private val busHelperInstance = getInstanceRetrofit(VehicleType.BUS).create<ActualDeparturesRetrofitApi>()
    private val tramHelperInstance = getInstanceRetrofit(VehicleType.TRAM).create<ActualDeparturesRetrofitApi>()
    private  fun getInstanceRetrofit(busOrTram: VehicleType): Retrofit {
        return Retrofit.Builder().baseUrl(busOrTram.baseUrlTtss)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    override fun getBusVehicleDepart(
        idStopPoint: String,
        callbackResponse: (Response<Departures>) -> Unit
    ) {
        return generateGetBusVehicleDepart(idStopPoint,busHelperInstance,callbackResponse)
    }

    override fun getTramVehicleDepart(
        idStopPoint: String,
        callbackResponse: (Response<Departures>) -> Unit
    ) {
        return generateGetTramVehicleDepart(idStopPoint,tramHelperInstance,callbackResponse)
    }






    private fun generateGetBusVehicleDepart(idStopPoint: String, retrofitAp:ActualDeparturesRetrofitApi, callbackResponse: (Response<Departures>) -> Unit){
        return retrofitAp.getDepratures(idStopPoint,"deprature").enqueue(object:
            Callback<Departures> {
            override fun onResponse(call: Call<Departures>, response: Response<Departures>) {


                Log.e("ojej",call.request().url().toString())
                if(response.body()!=null && response.isSuccessful) {
                    callbackResponse(response)
                }
            }



            override fun onFailure(call: Call<Departures>, t: Throwable) {

            }
        })
    }



    private fun generateGetTramVehicleDepart(idStopPoint: String, retrofitAp:ActualDeparturesRetrofitApi, callbackResponse: (Response<Departures>) -> Unit){
        return retrofitAp.getDepratures(idStopPoint,"deprature").enqueue(object:
            Callback<Departures> {
            override fun onResponse(call: Call<Departures>, response: Response<Departures>) {

                //if(response!=null && response.isSuccessful){

                if(response.body()!=null && response.isSuccessful) {
                    callbackResponse(response)
                }

                //}


            }



            override fun onFailure(call: Call<Departures>, t: Throwable) {

            }
        })
    }








}