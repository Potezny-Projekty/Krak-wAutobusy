package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface FavouriteLineInterface {


    fun getAllFavouriteLine(db: SQLiteDatabase):ArrayList<FavouriteLineData>

    fun isLineFavourite(db:SQLiteDatabase,numberLine: Int):Boolean

    fun addLineToFavorite(db:SQLiteDatabase, numberLine:Int)

    fun removeLineFromFavourite(db:SQLiteDatabase,numberLine: Int)


}