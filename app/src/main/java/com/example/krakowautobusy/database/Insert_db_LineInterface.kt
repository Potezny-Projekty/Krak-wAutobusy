package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface Insert_db_LineInterface {

    fun insertRow(db: SQLiteDatabase, row:LineRow):Long



    data class LineRow(
        val id: Long,
        val lineNumber: String,
        val firstBusStop: Long,
        val lastBusStop: Long,
        val vehicleType:Insert_db_VehicleTypeInterface.Vehicle
       )
}