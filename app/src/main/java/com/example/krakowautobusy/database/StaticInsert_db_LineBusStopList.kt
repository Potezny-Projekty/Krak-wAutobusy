package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class StaticInsert_db_LineBusStopList:Insert_db_LineBusStopList {
    val KEY_ID_LINE_BUS_STOP_LIST = "lineBusStopListId"

    val KEY_NUMBER_LINE = "lineNumber"
    val KEY_CONSECUTIVE_NUMBER = "consecutiveNumber"
    val TABLE_LINE_BUS_STOP_LIST = "lineBusStopListTable"
    val KEY_ID_BUS_STOP = "idBusStop"
    override fun insertRow(db: SQLiteDatabase, row: Insert_db_LineBusStopList.LineRow): Long {
        val contentValues = ContentValues()

        contentValues.put(KEY_NUMBER_LINE,row.lineNumber )
        contentValues.put(KEY_CONSECUTIVE_NUMBER,row.position )
        contentValues.put(KEY_ID_BUS_STOP ,row.api_short_name )



        val success = db.insert(TABLE_LINE_BUS_STOP_LIST, null, contentValues)

        return success
    }
}