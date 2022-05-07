package com.example.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Line:LineInteerface {


    fun returnLineFromCursor(
        cursor: Cursor,
        vehicleType: VehicleType
    ): LineData {

        return   LineData(
            cursor.getLong(LineTable.ID_LINE.indexColumn),
            cursor.getLong(LineTable.NUMBER_LINE.indexColumn),
            cursor.getLong(LineTable.FIRST_STOP_ID.indexColumn),
            cursor.getLong(LineTable.LAST_STOP_ID.indexColumn),
            vehicleType,
            //PLECEHOLDER
        "Przystanek pcozątkowy nazwa","Przystanek końcowy nazwa",true


        ///PLECEHOLDER ZAMIENIC NA DANE

        )

    }



    override fun getInfoAboutLineConcretDirectionName(
        db: SQLiteDatabase,
        numberLine: Long,
        lastStopName: String
    ): LineData {

        val columnReturns = arrayOf(
            LineTable.ID_LINE.nameColumn,LineTable.NUMBER_LINE.nameColumn,
            LineTable.FIRST_STOP_ID.nameColumn,LineTable.LAST_STOP_ID.nameColumn,
            LineTable.ID_VEHICLE.nameColumn
        )


       val baseQuery="SELECT "+columnReturns.joinToString(",")+" from Line join VehicleStopSequence on VehicleStopSequence.idLine=Line.idLine join VehicleStop on VehicleStop.idStopPoint=Line.lastStop where  "


        val filterCondition = "${LineTable.NUMBER_LINE.nameColumn}=${numberLine} and  ${LineTable.LAST_STOP_ID.nameColumn}=(SELECT Line.lastStop from Line join VehicleStop on VehicleStop.idStopPoint=Line.lastStop where VehicleStop.name like \"%${lastStopName}%\" )"
// arrayOf(numberLine.toString(),lastStopId.toString())

        val cursor = db.rawQuery(baseQuery+filterCondition, null
        )

        Log.e("hmm",baseQuery+filterCondition)
        var findVehicleStopType: LineData? =null
        if (cursor!!.moveToFirst()) {

            findVehicleStopType=
                if (LineTable.ID_VEHICLE.indexColumn.let { cursor.getInt(it) } == VehicleType.BUS.number) {
                    returnLineFromCursor(cursor,VehicleType.BUS)
                } else {
                    returnLineFromCursor(cursor,VehicleType.TRAM)
                }


        }
        cursor.close()
        return  findVehicleStopType!!




    }

    override fun getInfoAboutLineConcretDirectionId(
        db: SQLiteDatabase,
        numberLine: Long,
        lastStopId: Long
    ): LineData {



        val columnReturns = arrayOf(
            LineTable.ID_LINE.nameColumn,LineTable.NUMBER_LINE.nameColumn,
            LineTable.FIRST_STOP_ID.nameColumn,LineTable.LAST_STOP_ID.nameColumn,
            LineTable.ID_VEHICLE.nameColumn
        )

        val filterCondition = "${LineTable.NUMBER_LINE.nameColumn}=? and ${LineTable.LAST_STOP_ID.nameColumn}=?"


        val cursor = db.query(
            TableName.LINE.nameTable,
            columnReturns,
            filterCondition,
            arrayOf(numberLine.toString(),lastStopId.toString()),
            null,
            null,
            null,
            null
        )
        var findVehicleStopType: LineData? =null
        if (cursor!!.moveToFirst()) {

             findVehicleStopType=
                if (LineTable.ID_VEHICLE.indexColumn.let { cursor.getInt(it) } == VehicleType.BUS.number) {
                    returnLineFromCursor(cursor,VehicleType.BUS)
                } else {
                    returnLineFromCursor(cursor,VehicleType.TRAM)
                }


        }
        cursor.close()
        return  findVehicleStopType!!
    }

    override fun getInfoAboutLinesAnyDirection(db: SQLiteDatabase, numberLine: Long): ArrayList<LineData> {
        TODO("Not yet implemented Kurwa ")
    }

    override fun getInfoAllLine(db: SQLiteDatabase): ArrayList<LineData> {
        TODO("Not yet implemented")
    }

    override fun getInfoAboutLinePatternName(db: SQLiteDatabase, patternName: String) {
        TODO("Not yet implemented")
    }

    override fun getInfoAboutLinePatternNumber(db: SQLiteDatabase, patternNumber: Int) {
        TODO("Not yet implemented")
    }

    override fun getInfoAboutLinePatternFirstOrLastStopName(
        db: SQLiteDatabase,
        patternVehicleStopName: String
    ) {
        TODO("Not yet implemented")
    }

    override fun getVehicleStopsLine(db: SQLiteDatabase, idLine: Int) {
        TODO("Not yet implemented")
    }
}