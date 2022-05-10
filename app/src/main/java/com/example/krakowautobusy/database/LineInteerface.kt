package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

interface LineInteerface {
    fun getInfoAboutLineConcretDirectionName(db: SQLiteDatabase, numberLine:Long, lastStopName:String):LineData
    fun getInfoAboutLineConcretDirectionId(db: SQLiteDatabase, numberLine:Long, lastStopId:Long):LineData
    fun getInfoAboutLinesAnyDirection(db: SQLiteDatabase, numberLine:Long):ArrayList<LineData>
    fun getInfoAllLine(db:SQLiteDatabase):ArrayList<LineData>
    fun getInfoAboutLinePatternName(db: SQLiteDatabase,patternName:String):ArrayList<LineData>
    fun getInfoAboutLinePatternNumber(db: SQLiteDatabase,patternNumber:Int):ArrayList<LineData>
    fun getInfoAboutLinePatternFirstOrLastStopName(db: SQLiteDatabase,patternVehicleStopName:String):ArrayList<LineData>
    fun getVehicleStopsLine(db: SQLiteDatabase,idLine:Int):ArrayList<SequenceVehicleStopData>
    fun getAllLineWithAnyVehicleStopFitPattern(db:SQLiteDatabase, nameVehicleStop:String):ArrayList<LineData>


}