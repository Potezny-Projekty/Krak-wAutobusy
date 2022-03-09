package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface Insert_db_VehicleTypeInterface {
    fun insertVehicleRowToDb(db:SQLiteDatabase, row:Insert_db_VehicleTypeInterface.VehicleDatabaseRow):Long


    enum class Vehicle(val kind: String) {
        BUS("BUS"),
        TRAM("TRAM")
    }
   data class VehicleDatabaseRow(val name:String)


}