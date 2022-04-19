package com.example.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

interface VehicleStopData {


    data class VehicleStopPoint(val idVehicleStop:Long, val name:String, val longitude:Long, val latitude:Long, val idShort:Int, val vehicleType:Vehicle, val idStopPoint:Int )
    fun getVehicleStopsByID(db: SQLiteDatabase, id:Long):ArrayList<VehicleStopData.VehicleStopPoint>

    fun selectBusStopAll(db: SQLiteDatabase):ArrayList<VehicleStopData.VehicleStopPoint>

    fun returnVehicleStopPointFromCursor(cursor: Cursor,vehicle: Vehicle):VehicleStopPoint

}