package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface Insert_db_BusStopInterface {

    fun insertRow(db: SQLiteDatabase, row:BusStopRow):Long



    data class BusStopRow(
        val category:Insert_db_VehicleTypeInterface.Vehicle,
        val otherid: Long,
        val latitude:Int,
        val longitude:Int,
        val name:String,
        val stopPointNumber:String,
        val shortName:String)
}