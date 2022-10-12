package com.krak.krakowautobusy.ui.map.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@Deprecated("Propably no usage. New class in package inner network ttss ")
object RetrofitHelperBus {
    private const val baseUrl = "http://ttss.mpk.krakow.pl/internetservice/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}