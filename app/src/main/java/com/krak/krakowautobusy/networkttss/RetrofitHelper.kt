package com.krak.krakowautobusy.networkttss
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class no usage
 *
 */
@Deprecated("Acutally no usage")
class RetrofitHelper {

        fun getInstance(baseUrl:String): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

}