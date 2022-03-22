package com.example.krakowautobusy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val TABLE_LINE = "lineTable"
    val TABLE_BUS_STOP = "busStopTable"
    val TABLE_FAVOURITE = "favouriteTable"
    val TABLE_LINE_BUS_STOP_LIST = "lineBusStopListTable"
    val TABLE_VEHICLE_TYPE = "vehicleTypeListTable"
    val KEY_ID = "id"
    val KEY_ID_FAVOURITE = "favouriteId"
    val KEY_ID_BUS_STOP = "idBusStop"
    val KEY_ID_LINE_BUS_STOP_LIST = "lineBusStopListId"
    val KEY_ID_VEHICLE_TYPE = "vehicleTypeId"
    val KEY_NAME_BUS_STOP = "nameBusStop"
    val KEY_NUMBER_LINE = "lineNumber"
    val KEY_NUMBER_LINE_REVERSED = "lineNumberReversed"
    val KEY_LINE_NAME = "lineName"
    val KEY_LAST_BUS_STOP = "lastBusStopName"
    val KEY_FIRST_BUS_STOP = "firstBusStopName"
    val ALL_BUS_STOPS_IN_LINE = "lastBusStopName"
    val KEY_LATTITUDE = "lattitude"
    val KEY_LONGTITUDE = "longtitude"
    val KEY_CONSECUTIVE_NUMBER = "consecutiveNumber"
    val KEY_VEHICLE_TYPE = "vehicleType"
    val KEY_KIND_OF_BUS_STOP="kinOfBusStop"

    val KEY_BUS_STOP_OTHERID="api_id"
    val KEY_BUS_STOP_SHORT_NAME="IdShortBusStop"
    val KEY_STOP_POINT_NUMBER="stopPoint"

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
        Log.e("coś","tworze")

        val createVehicleTypeTable = ("""CREATE TABLE  $TABLE_VEHICLE_TYPE (
                $KEY_ID_VEHICLE_TYPE INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_VEHICLE_TYPE TEXT NOT NULL)""")
        db?.execSQL(createVehicleTypeTable)

        val createBusStopTable = ("""CREATE TABLE  $TABLE_BUS_STOP  (
                $KEY_ID_BUS_STOP  INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NAME_BUS_STOP TEXT NOT NULL,
                $KEY_LONGTITUDE INT NOT NULL,
                $KEY_LATTITUDE INT NOT NULL,
                $KEY_BUS_STOP_SHORT_NAME INT NOT NULL,
                $KEY_KIND_OF_BUS_STOP INT NOT NULL,
                $KEY_STOP_POINT_NUMBER INT NOT NULL,
                 FOREIGN KEY($KEY_KIND_OF_BUS_STOP)
                REFERENCES $TABLE_VEHICLE_TYPE($KEY_ID_VEHICLE_TYPE)
                )""")
        Log.i("Database", createBusStopTable)
        db?.execSQL(createBusStopTable)

        val createFavouriteTable = ("""CREATE TABLE  $TABLE_FAVOURITE (
                $KEY_ID_FAVOURITE INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NUMBER_LINE INT NOT NULL,
                $KEY_NUMBER_LINE_REVERSED INT NOT NULL,
                FOREIGN KEY($KEY_NUMBER_LINE)
                REFERENCES $TABLE_LINE($KEY_ID),
                FOREIGN KEY($KEY_NUMBER_LINE_REVERSED)
                REFERENCES $TABLE_LINE($KEY_ID))""")
        db?.execSQL(createFavouriteTable)

        val createLineTable = ("""CREATE TABLE  $TABLE_LINE (
                $KEY_ID  INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_NUMBER_LINE TEXT NOT NULL,
                $KEY_FIRST_BUS_STOP INT NOT NULL,
                $KEY_LAST_BUS_STOP INT NOT NULL,
                $KEY_VEHICLE_TYPE INT NOT NULL,
                FOREIGN KEY($KEY_FIRST_BUS_STOP)
                REFERENCES $TABLE_BUS_STOP($KEY_ID_BUS_STOP),
                FOREIGN KEY($KEY_LAST_BUS_STOP)
                REFERENCES $TABLE_BUS_STOP($KEY_ID_BUS_STOP),
                FOREIGN KEY($KEY_VEHICLE_TYPE)
                REFERENCES $TABLE_VEHICLE_TYPE($KEY_ID_VEHICLE_TYPE))""")
        Log.i("Database", createLineTable)
        db?.execSQL(createLineTable)

        val createLineBusStopListTable = ("""CREATE TABLE  $TABLE_LINE_BUS_STOP_LIST (
                $KEY_ID_LINE_BUS_STOP_LIST INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NUMBER_LINE INT NOT NULL,
                $KEY_ID_BUS_STOP INT NOT NULL,
                $KEY_CONSECUTIVE_NUMBER INT NOT NULL
               )""")
        db?.execSQL(createLineBusStopListTable)



     //   val fill:FillDatabase=StaticFillDatabase(instance!!,StaticDatabaseVehicleType())
     //   fill.fill_VehicleTypeTable()
     //   Log.e("coś","x")



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}