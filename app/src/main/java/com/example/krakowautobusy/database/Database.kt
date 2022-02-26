package com.example.krakowautobusy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val TABLE_LINE = "lineTable"
    val TABLE_BUS_STOP = "busStopTable"
    val TABLE_FAVOURITE = "favourite"
    val KEY_ID = "id"
    val KEY_ID_FAVOURITE = "favouriteId"
    val KEY_ID_BUS_STOP = "idBusStop"
    val KEY_NAME_BUS_STOP = "nameBusStop"
    val KEY_NUMBER_LINE = "lineNumber"
    val KEY_LINE_NAME = "lineName"
    val KEY_LAST_BUS_STOP = "lastBusStopName"
    val KEY_FIRST_BUS_STOP = "firstBusStopName"
    val ALL_BUS_STOPS_IN_LINE = "lastBusStopName"

    companion object {

        val DATABASE_VERSION = 1
        val DATABASE_NAME = "busDatabase"

        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            if (instance == null) {
                instance = Database(context)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createLineTable = ("""CREATE TABLE  $TABLE_LINE (
                $KEY_ID  INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_FIRST_BUS_STOP TEXT,
                $KEY_LAST_BUS_STOP TEXT,
                $KEY_NUMBER_LINE TEXT,
                FOREIGN KEY($ALL_BUS_STOPS_IN_LINE)
                REFERENCES $TABLE_BUS_STOP($ALL_BUS_STOPS_IN_LINE))""")
        db?.execSQL(createLineTable)

        val createBusStopTable = ("""CREATE TABLE  $TABLE_BUS_STOP  (
                $KEY_ID_BUS_STOP  INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NAME_BUS_STOP TEXT)""")
        db?.execSQL(createBusStopTable)

        val createFavouriteTable = ("""CREATE TABLE  $TABLE_FAVOURITE (
                $KEY_ID_FAVOURITE INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NUMBER_LINE TEXT)""")
        db?.execSQL(createFavouriteTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}