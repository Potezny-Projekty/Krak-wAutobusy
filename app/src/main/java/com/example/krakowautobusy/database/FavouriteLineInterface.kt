package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface FavouriteLineInterface {

    fun isLineFavourite(db: SQLiteDatabase, idLine:Long):Boolean

    fun getAllFavouriteLine(db: SQLiteDatabase):ArrayList<FavouriteLineData>

    fun addLineToFavourite(db:SQLiteDatabase,idLine:Int)

    fun removeLineFromFavourite(db:SQLiteDatabase,idLine:Int)
}