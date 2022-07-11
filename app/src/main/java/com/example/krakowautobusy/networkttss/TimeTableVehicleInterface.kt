package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.Response

interface TimeTableVehicleInterface {
    fun getBusVehicleTimeTable(idVehicle:String,idTrip:String, callbackResponse: (Response<TimeTableData>) -> Unit): Unit
}