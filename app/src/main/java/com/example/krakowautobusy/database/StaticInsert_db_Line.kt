package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class StaticInsert_db_Line:Insert_db_LineInterface {

    val KEY_ID = "id"
    val KEY_NUMBER_LINE = "lineNumber"
    val KEY_LAST_BUS_STOP = "lastBusStopName"
    val KEY_FIRST_BUS_STOP = "firstBusStopName"
    val KEY_VEHICLE_TYPE = "vehicleType"
    val TABLE_LINE = "lineTable"
    override fun insertRow(db: SQLiteDatabase, row: Insert_db_LineInterface.LineRow): Long {
        val contentValues = ContentValues()
        contentValues.put(KEY_ID,row.id )
        contentValues.put(KEY_NUMBER_LINE,row.lineNumber )
        contentValues.put(KEY_LAST_BUS_STOP,row.lastBusStop )
        contentValues.put(KEY_FIRST_BUS_STOP,row.firstBusStop )


        if(row.vehicleType==Insert_db_VehicleTypeInterface.Vehicle.BUS){
            contentValues.put(KEY_VEHICLE_TYPE,1 )
        }else{
            contentValues.put(KEY_VEHICLE_TYPE,2 )
        }

        val success = db.insert(TABLE_LINE, null, contentValues)

        return success
    }
}