package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import retrofit2.Response

interface DeparturesVehicleInterface {

    fun getBusVehicleDepart(idStopPoint:String, callbackResponse: (Response<Departures>) -> Unit): Unit
    fun getTramVehicleDepart(idStopPoint:String, callbackResponse: (Response<Departures>) -> Unit): Unit
}