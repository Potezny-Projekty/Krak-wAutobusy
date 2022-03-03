package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class StaticDatabaseVehicleType:Database_VehicleType {
    val VEHICLE_TYPE_NAME = "vehicleType"
    val KEY_ID_VEHICLE_TYPE = "vehicleTypeId"
    val NAME_TABLE="vehicleTypeListTable"



    override fun insertVehicleTypeDataRowToDatabase(db: SQLiteDatabase,name:Database_VehicleType.VehicleDatabase):Long {


        val contentValues = ContentValues()
        contentValues.put(VEHICLE_TYPE_NAME,name.name )
        val success = db.insert(NAME_TABLE, null, contentValues)

        return success
    }
}