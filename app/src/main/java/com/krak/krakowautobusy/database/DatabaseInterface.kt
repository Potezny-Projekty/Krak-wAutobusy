package com.krak.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class DatabaseInterface(private var instance: Database) {

    private var db: SQLiteDatabase = instance.writableDatabase

    fun addLine(firstBusStop: String, lastBusStop: String, numberLine: Int): Long {
        db = instance.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(instance.KEY_FIRST_BUS_STOP, firstBusStop)
        contentValues.put(instance.KEY_LAST_BUS_STOP, lastBusStop)
        contentValues.put(instance.KEY_NUMBER_LINE, numberLine.toString())

        val success = db.insert(instance.TABLE_LINE, null, contentValues)
        //db.close()
        return success
    }

    fun updateLine(id: Int, firstBusStop: String, lastBusStop: String, numberLine: Int): Int {
        db = instance.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(instance.KEY_FIRST_BUS_STOP, firstBusStop)
        contentValues.put(instance.KEY_LAST_BUS_STOP, lastBusStop)
        contentValues.put(instance.KEY_NUMBER_LINE, numberLine)

        val success = db.update(instance.TABLE_LINE, contentValues, instance.KEY_ID + "= $id", null)
        //db.close()
        return success
    }

    fun deleteLine(id: Int): Int {
        db = instance.writableDatabase
        val success = db.delete(instance.TABLE_LINE, instance.KEY_ID + "= $id", null)
        //db.close()
        return success
    }

    fun getLine(id: String): String {
        db = instance.readableDatabase
        var line = ""
        val query =
                "SELECT ${instance.KEY_NUMBER_LINE} FROM ${instance.TABLE_LINE} WHERE ${instance.KEY_ID} == $id"
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