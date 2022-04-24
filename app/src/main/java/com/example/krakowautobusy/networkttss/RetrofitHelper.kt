package com.example.krakowautobusy.networkttss
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitHelper {



        fun getInstance(baseUrl:String): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

}