package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class StaticInsert_db_VehicleType:Insert_db_VehicleTypeInterface {
    val VEHICLE_TYPE_NAME = "vehicleType"
    val KEY_ID_VEHICLE_TYPE = "vehicleTypeId"
    val NAME_TABLE="vehicleTypeListTable"


    override fun insertVehicleRowToDb(db: SQLiteDatabase, row:Insert_db_VehicleTypeInterface.VehicleDatabaseRow):Long {

        val contentValues = ContentValues()
        contentValues.put(VEHICLE_TYPE_NAME,row.name )
        val success = db.insert(NAME_TABLE, null, contentValues)

        return success
    }
}