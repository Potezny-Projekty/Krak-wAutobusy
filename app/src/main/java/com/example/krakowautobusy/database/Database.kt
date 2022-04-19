package com.example.krakowautobusy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val TABLE_LINE = "lineTable"
    val KEY_ID = "id"
    val KEY_NUMBER_LINE = "lineNumber"
    val KEY_LAST_BUS_STOP = "lastBusStopName"
    val KEY_FIRST_BUS_STOP = "firstBusStopName"


    companion object {

        val DATABASE_VERSION = 1
        val DATABASE_NAME = "busDatabase"

        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            val loadDatabase=LoadDatabase()
            loadDatabase.importdb(context)


            if (instance == null) {
                instance = Database(context)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}