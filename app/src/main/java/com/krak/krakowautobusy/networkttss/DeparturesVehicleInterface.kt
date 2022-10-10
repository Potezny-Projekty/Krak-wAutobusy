package com.krak.krakowautobusy.networkttss

import retrofit2.Response

interface DeparturesVehicleInterface {
    fun getBusVehicleDepart(idStopPoint:String, callbackResponse: (Response<Departures>) -> Unit): Unit
    fun getTramVehicleDepart(idStopPoint:String, callbackResponse: (Response<Departures>) -> Unit): Unit
}