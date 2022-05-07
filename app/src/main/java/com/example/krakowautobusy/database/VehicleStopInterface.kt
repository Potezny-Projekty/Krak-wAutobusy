package com.example.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

interface VehicleStopInterface {



    fun getVehicleStopsByID(db: SQLiteDatabase, id:Long):VehicleStopData

    fun getAllVehicleStop(db: SQLiteDatabase):ArrayList<VehicleStopData>

    fun getAllVehicleStopLine(db: SQLiteDatabase):ArrayList<VehicleStopData>


    fun findNameBusStopById( db: SQLiteDatabase,idStop:Int):String



}