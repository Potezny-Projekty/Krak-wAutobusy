package com.example.krakowautobusy.database

data class LineData (val idLine:Long, val numberLine:Long, val firstStopId:Long,
                     val lastStopId:Long, val typeVehicle:VehicleType, val firstStopName:String, val lastStopName:String,
                     var isFavourite:Boolean, val busStopViaRoute:String="")