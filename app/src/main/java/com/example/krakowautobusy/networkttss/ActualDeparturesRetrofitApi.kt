package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActualDeparturesRetrofitApi {
    //internetservice/services/passageInfo/stopPassages/stop

    @GET("services/passageInfo/stopPassages/stopPoint")
    fun getDepratures(@Query("stopPoint") stopPoint : String,@Query("mode") mode : String) : Call<Departures>
}