package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class StaticInsert_db_BusStop:Insert_db_BusStopInterface {

    val KEY_LATTITUDE = "lattitude"
    val KEY_LONGTITUDE = "longtitude"
    val KEY_NAME_BUS_STOP = "nameBusStop"
    val KEY_ID_BUS_STOP = "idBusStop"
    val TABLE_BUS_STOP = "busStopTable"
    val KEY_BUS_STOP_OTHERID="api_id"
    val KEY_BUS_STOP_SHORT_NAME="api_short_name"
    val KEY_KIND_OF_BUS_STOP="kinOfBusStop"

    override fun insertRow(db: SQLiteDatabase, row: Insert_db_BusStopInterface.BusStopRow):Long{
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME_BUS_STOP,row.name )
        contentValues.put(KEY_LATTITUDE,row.latitude )
        contentValues.put(KEY_LONGTITUDE,row.longitude )
        contentValues.put(KEY_ID_BUS_STOP,row.otherid )
        contentValues.put(KEY_BUS_STOP_SHORT_NAME,row.shortName )

        if(row.category==Insert_db_VehicleTypeInterface.Vehicle.BUS){
            contentValues.put(KEY_KIND_OF_BUS_STOP,1 )
        }else{
            contentValues.put(KEY_KIND_OF_BUS_STOP,2 )
        }

        val success = db.insert(TABLE_BUS_STOP, null, contentValues)

        return success
    }
}