package com.krak.krakowautobusy.database

data class VehicleStopData(val idVehicleStop:Long, val name:String, val longitude:Long, val latitude:Long, val idShort:Int, val vehicleType:VehicleType, val idStopPoint:Int,
                           var isFavourite:Boolean )