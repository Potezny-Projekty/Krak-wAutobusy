package com.example.krakowautobusy.database

data class LineData (val idLine:Long,val numberLine:Long,val firstStopId:Long,
val lastStopId:Long,val typeVehicle:VehicleType)