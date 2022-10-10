package com.krak.krakowautobusy.networkttss

import kotlinx.serialization.Serializable



@Serializable
data class Departures (val actual:ArrayList<Depart>)

data class AllDepartures(var data:ArrayList<Departures>)

@Serializable
data class Depart (val actualTime:String,val direction:String,val patternText:String,val plannedTime:String )


