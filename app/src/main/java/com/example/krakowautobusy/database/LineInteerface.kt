package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface LineInteerface {
    fun getInfoAboutLineConcretDirectionName(db: SQLiteDatabase, numberLine:Long, lastStopName:String):LineData
    fun getInfoAboutLineConcretDirectionId(db: SQLiteDatabase, numberLine:Long, lastStopId:Long):LineData
    fun getInfoAboutLinesAnyDirection(db: SQLiteDatabase, numberLine:Long):ArrayList<LineData>
    fun getInfoAllLine(db:SQLiteDatabase):ArrayList<LineData>
    fun getInfoAboutLinePatternName(db: SQLiteDatabase,patternName:String)
    fun getInfoAboutLinePatternNumber(db: SQLiteDatabase,patternNumber:Int)
    fun getInfoAboutLinePatternFirstOrLastStopName(db: SQLiteDatabase,patternVehicleStopName:String)
    fun getVehicleStopsLine(db: SQLiteDatabase,idLine:Int)


}