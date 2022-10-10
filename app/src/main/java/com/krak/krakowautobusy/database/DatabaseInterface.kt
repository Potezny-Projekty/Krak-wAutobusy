package com.krak.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

@Deprecated("This method is for posible future or not", level = DeprecationLevel.WARNING)
class DatabaseInterface(private var instance: Database) {


    val TABLE_LINE = "lineTable"
    val KEY_ID = "id"
    val KEY_NUMBER_LINE = "lineNumber"
    val KEY_LAST_BUS_STOP = "lastBusStopName"
    val KEY_FIRST_BUS_STOP = "firstBusStopName"
    private var db: SQLiteDatabase = instance.writableDatabase

    fun addLine(firstBusStop: String, lastBusStop: String, numberLine: Int): Long {
        db = instance.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_FIRST_BUS_STOP, firstBusStop)
        contentValues.put(KEY_LAST_BUS_STOP, lastBusStop)
        contentValues.put(KEY_NUMBER_LINE, numberLine.toString())

        val success = db.insert(TABLE_LINE, null, contentValues)
        //db.close()
        return success
    }

    fun updateLine(id: Int, firstBusStop: String, lastBusStop: String, numberLine: Int): Int {
        db = instance.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_FIRST_BUS_STOP, firstBusStop)
        contentValues.put(KEY_LAST_BUS_STOP, lastBusStop)
        contentValues.put(KEY_NUMBER_LINE, numberLine)

        val success = db.update(TABLE_LINE, contentValues, KEY_ID + "= $id", null)
        //db.close()
        return success
    }

    fun deleteLine(id: Int): Int {
        db = instance.writableDatabase
        val success = db.delete(TABLE_LINE, KEY_ID + "= $id", null)
        //db.close()
        return success
    }

    fun getLine(id: String): String {
        db = instance.readableDatabase
        var line = ""
        val query =
                "SELECT ${KEY_NUMBER_LINE} FROM ${TABLE_LINE} WHERE ${KEY_ID} == $id"
        val cursor = db.rawQuery(query, null)
        if (cursor!!.moveToFirst()) {
            do {
                line = cursor.getString(0)
            } while (cursor.moveToNext())
        }
        cursor.close()
        //db.close()
        return line
    }
}