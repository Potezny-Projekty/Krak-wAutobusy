package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DatabaseInterface(private var instance: Database) {

    private var db: SQLiteDatabase = instance.writableDatabase

    fun addLine(firstBusStop: String, lastBusStop: String, numberLine: Int): Long {
        db = instance.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(instance.KEY_FIRST_BUS_STOP, firstBusStop)
        contentValues.put(instance.KEY_LAST_BUS_STOP, lastBusStop)
        contentValues.put(instance.KEY_NUMBER_LINE, numberLine.toString())

        val success = db.insert(instance.TABLE_LINE, null, contentValues)
        db.close()
        return success
    }

    fun updateLine(id: Int,firstBusStop: String, lastBusStop: String, numberLine: Int){

    }
    fun removeLine(id: Int){

    }
    fun getLine(id: Int){

    }
    fun addBusStop(): Long {
        db = instance.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(instance.KEY_NAME_BUS_STOP, "XDDDDDD")

        val success = db.insert(instance.TABLE_BUS_STOP, null, contentValues)
        db.close()
        return success
    }
}