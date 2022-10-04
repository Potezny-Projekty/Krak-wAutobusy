package com.krak.krakowautobusy.networkttss

import kotlinx.serialization.Serializable



@Serializable
data class Departures (val actual:ArrayList<Depart>)

data class AllDepartures(var data:ArrayList<Departures>)

@Serializable
data class Depart (val actualTime:String,val direction:String,val patternText:String,val plannedTime:String )

/*

"actualRelativeTime": -24,
      "actualTime": "12:08",
      "direction": "Czerwone Maki P+R",
      "mixedTime": "0 %UNIT_MIN%",
      "passageid": "-1188950300538692112",
      "patternText": "18",
      "plannedTime": "12:08",
      "routeId": "8059228650286874693",
      "status": "STOPPING",
      "tripId": "8059232507170029577",
      "vehicleId": "-1188950296125760256"

 */

