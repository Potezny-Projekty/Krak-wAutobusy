package com.krak.krakowautobusy.networkttss

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActualDeparturesRetrofitApi {
    @GET("services/passageInfo/stopPassages/stopPoint")
    fun getDepratures(@Query("stopPoint") stopPoint : String,@Query("mode") mode : String) : Call<Departures>
}