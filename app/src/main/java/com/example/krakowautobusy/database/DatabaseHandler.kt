package example.javatpoint.com.kotlinsqlitecrud

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "busDatabase"
        private const val TABLE_LINE = "lineTable"
        private const val TABLE_BUS_STOP = "busStopTable"
        private const val FAVOURITE = "favourite"
        private const val KEY_ID = "id"
        private const val KEY_ID_FAVOURITE = "favouriteId"
        private const val KEY_ID_BUS_STOP = "idBusStop"
        private const val KEY_NAME_BUS_STOP = "nameBusStop"
        private const val KEY_NUMBER_LINE = "lineNumber"
        private const val KEY_LINE_NAME = "lineName"
        private const val KEY_LAST_BUS_STOP = "lastBusStopName"
        private const val KEY_FIRST_BUS_STOP = "lastBusStopName"
        private const val ALL_BUS_STOPS_IN_LINE = "lastBusStopName"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createLineTable = ("""CREATE TABLE  $TABLE_LINE (
                $KEY_ID  INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_FIRST_BUS_STOP TEXT,
                $KEY_LAST_BUS_STOP TEXT,
                $KEY_NUMBER_LINE TEXT,
                FOREIGN KEY($ALL_BUS_STOPS_IN_LINE),
                REFERENCES $TABLE_BUS_STOP($ALL_BUS_STOPS_IN_LINE))""")
        db?.execSQL(createLineTable)

        val createBusStopTable = ("""CREATE TABLE  $TABLE_BUS_STOP  (
                $KEY_ID_BUS_STOP  INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NAME_BUS_STOP TEXT)""")
        db?.execSQL(createBusStopTable)

        val createFavouriteTable = ("""CREATE TABLE  $FAVOURITE (
                $KEY_ID_FAVOURITE INTEGER PRIMARY KEY AUTOINCREMENT, 
                $KEY_NUMBER_LINE TEXT)""")
        db?.execSQL(createFavouriteTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun addLine(): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_LINE_NAME, "test2")
        contentValues.put(KEY_NUMBER_LINE, 25)

        val success = db.insert(TABLE_LINE, null, contentValues)
        //db.close()
        return success
    }

    fun addBusStop(): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME_BUS_STOP, "TEST5552")

        val success = db.insert(TABLE_BUS_STOP, null, contentValues)
        //db.close()
        return success
    }
}