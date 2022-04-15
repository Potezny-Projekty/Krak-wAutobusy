package com.example.krakowautobusy.ui.map.vehicledata

data class LineBusData(val id: Int, val isBus: VehicleEnum, val numberLine:Int, val startBusStop:String,
                       val endBusStop:String, val isFavourite:Boolean)

enum class VehicleEnum{BUS,TRAM}