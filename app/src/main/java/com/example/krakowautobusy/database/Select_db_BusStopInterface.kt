package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface Select_db_BusStopInterface {
    enum class Vehicle(val kind: String) {
        BUS("BUS"),
        TRAM("TRAM")
    }

    data class BusStopRow(val id:Long,val nameBusStop:String,val longitude:Long,val latitude:Long,val idOuter:Int,val kindVehicle:Vehicle,val stopPoint:Int )
fun selectBusStop(db: SQLiteDatabase, id:Long):ArrayList<Select_db_BusStopInterface.BusStopRow>

}