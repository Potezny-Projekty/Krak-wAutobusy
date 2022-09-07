package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface FavouriteVehicleStopInterface {
    fun getAllFavouriteVehicleStop(db: SQLiteDatabase):ArrayList<VehicleStopData>

    fun isVehicleStopFavourite(db: SQLiteDatabase, nameVehicleStop: String):Boolean

    fun addVehicleStopToFavorite(db: SQLiteDatabase, nameVehicleStop: String)

    fun removeVehicleStopFromFavourite(db: SQLiteDatabase, nameVehicleStop: String)


    fun isVehicleStopFavouriteById(db: SQLiteDatabase, IdVehicleStop: String):Boolean

    fun addVehicleStopToFavoriteById(db: SQLiteDatabase, IdVehicleStop: String)

    fun removeVehicleStopFromFavouriteById(db: SQLiteDatabase, IdVehicleStop: String)


}