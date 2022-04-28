package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface LineInteerface {
    fun getVehicleStopsLinesWithConcretDirection(db: SQLiteDatabase, numberLine:Long,lastStopId:Long):Boolean
    fun getInfoAboutLineConcretDirection(db: SQLiteDatabase, numberLine:Long,lastStopId:Long):LineData
    fun getInfoAboutLinesAnyDirection(db: SQLiteDatabase, numberLine:Long):ArrayList<LineData>
}