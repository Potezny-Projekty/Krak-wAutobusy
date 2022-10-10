package com.krak.krakowautobusy.networkttss

import com.krak.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.google.gson.JsonObject
import retrofit2.Response

interface PositionVehicleInterface {
    fun getBusPosition(lastUpdate:Long, callbackResponse: (Response<AllVehicles>) -> Unit): Unit
    fun getAllVehiclePosition()
    fun getTramPosition(lastUpdate:Long, callbackResponse: (Response<AllVehicles>) -> Unit): Unit
    fun getTramPath(idVehicle:String, callbackResponse: (Response<JsonObject>) -> Unit): Unit
    fun getBusPath(idVehicle:String, callbackResponse: (Response<JsonObject>) -> Unit): Unit
}