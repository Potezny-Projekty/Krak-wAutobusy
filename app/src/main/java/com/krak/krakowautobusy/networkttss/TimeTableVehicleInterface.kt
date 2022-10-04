package com.krak.krakowautobusy.networkttss

import com.krak.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.Response

interface TimeTableVehicleInterface {
    fun getBusVehicleTimeTable(idVehicle:String,idTrip:String, callbackResponse: (Response<TimeTableData>) -> Unit): Unit
    fun getTramVehicleTimeTable(idVehicle:String,idTrip:String, callbackResponse: (Response<TimeTableData>) -> Unit): Unit
}