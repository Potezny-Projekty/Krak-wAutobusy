package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import retrofit2.Response

interface ActualPositionVehicleInterface {
    fun getBusPosition(lastUpdate:Long, callbackResponse: (Response<AllVehicles>) -> Unit): Unit
    fun getAllVehiclePosition()
    fun getTramPosition(lastUpdate:Long, callbackResponse: (Response<AllVehicles>) -> Unit): Unit

}