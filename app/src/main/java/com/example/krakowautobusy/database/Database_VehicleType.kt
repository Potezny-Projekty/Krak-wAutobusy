package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

interface Database_VehicleType {
    fun insertVehicleTypeDataRowToDatabase(db:SQLiteDatabase,name:Database_VehicleType.VehicleDatabase):Long


    enum class Vehicle(val kind: String) {
        BUS("BUS"),
        TRAIN("TRAIN")
    }
   data class VehicleDatabase(val name:String)


}