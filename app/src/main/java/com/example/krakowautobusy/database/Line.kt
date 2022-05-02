package com.example.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Line:LineInteerface {

    val TABLE_LINE="Line"

    val ID_LINE_COL = "$TABLE_LINE.idLine"
    val NUMBER_LINE_COL = "numberLine"
    val FIRST_STOP_ID_COL = "firstStop"
    val LAST_STOP_ID_COL = "lastStop"
    val ID_VEHICLE_COL = "idVehicle"


    val COLUMN_LINE_TABLE_NUMBER = LinkedHashMap<String, Int>()

    init {
        COLUMN_LINE_TABLE_NUMBER[ID_LINE_COL] = 0
        COLUMN_LINE_TABLE_NUMBER[NUMBER_LINE_COL] = 1
        COLUMN_LINE_TABLE_NUMBER[FIRST_STOP_ID_COL] = 2
        COLUMN_LINE_TABLE_NUMBER[LAST_STOP_ID_COL] = 3
        COLUMN_LINE_TABLE_NUMBER[ID_VEHICLE_COL] = 4
    }



    fun returnLineFromCursor(
        cursor: Cursor,
        vehicleType: VehicleType
    ): LineData {

        return   LineData(
            cursor.getLong(COLUMN_LINE_TABLE_NUMBER[ID_LINE_COL]!!),
            cursor.getLong(COLUMN_LINE_TABLE_NUMBER[NUMBER_LINE_COL]!!),
            cursor.getLong(COLUMN_LINE_TABLE_NUMBER[FIRST_STOP_ID_COL]!!),
            cursor.getLong(COLUMN_LINE_TABLE_NUMBER[LAST_STOP_ID_COL]!!),
            vehicleType

        )

    }








    override fun getInfoAboutLineConcretDirectionName(
        db: SQLiteDatabase,
        numberLine: Long,
        lastStopName: String
    ): LineData {

       //
        val columnReturns = arrayOf(
            ID_LINE_COL,NUMBER_LINE_COL,FIRST_STOP_ID_COL,LAST_STOP_ID_COL,ID_VEHICLE_COL
        )
       val baseQuery="  select "+columnReturns.joinToString(",")+" from Line join VehicleStopSequence on VehicleStopSequence.idLine=Line.idLine join VehicleStop on VehicleStop.idStopPoint=Line.lastStop where  "


        val filterCondition = "${NUMBER_LINE_COL}=${numberLine} and  ${LAST_STOP_ID_COL}=(SELECT Line.lastStop from Line join VehicleStop on VehicleStop.idStopPoint=Line.lastStop where VehicleStop.name like \"%${lastStopName}%\" )"
// arrayOf(numberLine.toString(),lastStopId.toString())

        val cursor = db.rawQuery(baseQuery+filterCondition, null
        )

        Log.e("hmm",baseQuery+filterCondition)
        var findVehicleStopType: LineData? =null
        if (cursor!!.moveToFirst()) {

            findVehicleStopType=
                if (COLUMN_LINE_TABLE_NUMBER[ID_VEHICLE_COL]?.let { cursor.getInt(it) } == VehicleType.BUS.number) {
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
            ID_LINE_COL,NUMBER_LINE_COL,FIRST_STOP_ID_COL,LAST_STOP_ID_COL,ID_VEHICLE_COL
        )
        val filterCondition = "${NUMBER_LINE_COL}=? and ${LAST_STOP_ID_COL}=?"


        val cursor = db.query(
            TABLE_LINE,
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
                if (COLUMN_LINE_TABLE_NUMBER[ID_VEHICLE_COL]?.let { cursor.getInt(it) } == VehicleType.BUS.number) {
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
}