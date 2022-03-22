package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface Insert_db_LineBusStopList {
    fun insertRow(db: SQLiteDatabase, row:LineRow):Long



    data class LineRow(

        val lineNumber: String,

        val api_short_name: Long,
        val position:Long
    )
}