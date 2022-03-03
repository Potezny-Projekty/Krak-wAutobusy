package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface FillDatabase {

    var instance: Database
    var vehicleDatabase:Database_VehicleType


    fun fill_VehicleTypeTable(){








    }

    fun fill_BusStopTable(){

    }

    fun fill_FavouriteTable(){

    }

    fun fill_LineTable(){

    }


    fun fill_LineBusStop(){

    }



}