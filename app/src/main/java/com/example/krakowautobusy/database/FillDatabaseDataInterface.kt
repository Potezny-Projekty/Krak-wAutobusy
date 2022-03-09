package com.example.krakowautobusy.database

interface FillDatabaseDataInterface {

    var instance: Database
    var insertVehicleDatabase:Insert_db_VehicleTypeInterface
    var insertBusStopDatabase:Insert_db_BusStopInterface
    var insertLineDatabase:Insert_db_LineInterface


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