package com.krak.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.krak.krakowautobusy.api.Api


const val SEQUENCE_NUMBER_COL=0
const val NAME_VEHICLE_STOP_COL=1
const val LONGITUDE_COLS=2
const val LATITUDE_COLS=3
const val IDSTOP_POINT_COL=4

class Line:LineInteerface {


    private fun returnLineFromCursor(
        cursor: Cursor,
        vehicleType: VehicleType,stopName:String=""
    ): LineData {

        val firstVehicleStopName=Api.getApi().getVehicleStopById( cursor.getLong(LineTable.FIRST_STOP_ID.indexColumn)).name
        val lastVehicleStopName=Api.getApi().getVehicleStopById(cursor.getLong(LineTable.LAST_STOP_ID.indexColumn)).name
        val isLineFavourite=Api.getApi().isLineFavourite(cursor.getInt(LineTable.NUMBER_LINE.indexColumn))

        return   LineData(
            cursor.getLong(LineTable.ID_LINE.indexColumn),
            cursor.getLong(LineTable.NUMBER_LINE.indexColumn),
            cursor.getLong(LineTable.FIRST_STOP_ID.indexColumn),
            cursor.getLong(LineTable.LAST_STOP_ID.indexColumn),
            vehicleType,
            firstVehicleStopName,lastVehicleStopName,isLineFavourite,stopName,
        )

    }


    private fun returnVehicleStopSequenceFromCursor(
        cursor: Cursor
    ): SequenceVehicleStopData {
        return   SequenceVehicleStopData(
            cursor.getInt(SEQUENCE_NUMBER_COL),cursor.getString(NAME_VEHICLE_STOP_COL),
            cursor.getLong(LONGITUDE_COLS),cursor.getLong(LATITUDE_COLS),cursor.getInt(IDSTOP_POINT_COL)

        )
    }



    override fun getInfoAboutLineConcretDirectionName(
        db: SQLiteDatabase,
        numberLine: Int,
        lastStopName: String
    ): LineData {

        val columnReturns = arrayOf(
            LineTable.ID_LINE.nameColumn,LineTable.NUMBER_LINE.nameColumn,
            LineTable.FIRST_STOP_ID.nameColumn,LineTable.LAST_STOP_ID.nameColumn,
            LineTable.ID_VEHICLE.nameColumn
        )


        val baseQuery="SELECT "+columnReturns.joinToString(",")+" from Line join VehicleStopSequence on VehicleStopSequence.idLine=Line.idLine join VehicleStop on VehicleStop.idStopPoint=Line.lastStop where  "
        val filterCondition = "${LineTable.NUMBER_LINE.nameColumn}=${numberLine} and  ${LineTable.LAST_STOP_ID.nameColumn}=(SELECT Line.lastStop from Line join VehicleStop on VehicleStop.idStopPoint=Line.lastStop where VehicleStop.name like \"%${lastStopName}%\" )"


        val cursor = db.rawQuery(baseQuery+filterCondition, null
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


    /**
     * This method possible implemented in future
     * @suppress no implemented
     */
    override fun getInfoAboutLinesAnyDirection(db: SQLiteDatabase, numberLine: Long): ArrayList<LineData> {
        TODO("Not yet implemented")
    }

    /**
     * This method possible implemented in future
     * @suppress no implemented
     */
    override fun getInfoAllLine(db: SQLiteDatabase): ArrayList<LineData> {
        TODO("Not yet implemented")
    }

    /**
     * This method possible implemented in future
     * @suppress no implemented
     */
    override fun getInfoAboutLinePatternName(db: SQLiteDatabase, patternName: String): ArrayList<LineData> {
        TODO("Not yet implemented")
    }

    override fun getInfoAboutLinePatternNumber(db: SQLiteDatabase, patternNumber: Int): ArrayList<LineData> {

        val columnReturns = arrayOf(
            LineTable.ID_LINE.nameColumn,LineTable.NUMBER_LINE.nameColumn,
            LineTable.FIRST_STOP_ID.nameColumn,LineTable.LAST_STOP_ID.nameColumn,
            LineTable.ID_VEHICLE.nameColumn
        )

        val filterCondition = "cast(${LineTable.NUMBER_LINE.nameColumn} as text) like \"${patternNumber}%\""


        val cursor = db.query(
            TableName.LINE.nameTable,
            columnReturns,
            filterCondition,
            null,
            null,
            null,
            null,
            null
        )

        val lineDataMatchToPattern= arrayListOf<LineData>()
        var findVehicleStopType: LineData?
        if (cursor!!.moveToFirst()) {
            do {


                findVehicleStopType=
                 if (LineTable.ID_VEHICLE.indexColumn.let { cursor.getInt(it) } == VehicleType.BUS.number) {
                      returnLineFromCursor(cursor,VehicleType.BUS)
                  } else {
                     returnLineFromCursor(cursor,VehicleType.TRAM)
                  }
                lineDataMatchToPattern.add(findVehicleStopType)

            }while(cursor.moveToNext())


        }
        cursor.close()
        return  lineDataMatchToPattern
    }

    /**
     * This method possible implemented in future
     * @suppress no implemented
     */
    override fun getInfoAboutLinePatternFirstOrLastStopName(
        db: SQLiteDatabase,
        patternVehicleStopName: String
    ): ArrayList<LineData> {
        TODO("Not yet implemented")
    }

    override fun getVehicleStopsLine(db: SQLiteDatabase, idLine: Int): ArrayList<SequenceVehicleStopData> {
        val baseQuery="select DISTINCT VehicleStopSequence.sequenceNumber,VehicleStop.name,VehicleStop.lattitude,VehicleStop.longtitude,VehicleStop.idShort from Line join VehicleStopSequence on VehicleStopSequence.idLine=Line.idLine join VehicleStop on VehicleStop.idStopPoint=VehicleStopSequence.idVehicleStop where Line.numberLine=${idLine} order by sequenceNumber"

        val cursor = db.rawQuery(baseQuery, null
        )

        val linedatas:ArrayList<SequenceVehicleStopData> = ArrayList()

        if (cursor!!.moveToFirst()) {

            do {
                var vehicleSeq:SequenceVehicleStopData
                    vehicleSeq=  returnVehicleStopSequenceFromCursor(cursor)
                    linedatas.add(vehicleSeq)


            }while(cursor.moveToNext())

        }
        cursor.close()
        return  linedatas
    }


    override fun getAllLine(db: SQLiteDatabase): ArrayList<LineData> {

        val baseQuery="SELECT    Line.idLine,  Line.numberLine,Line.firstStop,Line.lastStop,Line.idVehicle from Line join FavouriteLine on FavouriteLine.idLine=Line.idLine "

        val cursor = db.rawQuery(baseQuery, null
        )

        val linedatas:ArrayList<LineData> = ArrayList()

        if (cursor!!.moveToFirst()) {

            do {
                var lineData:LineData

                lineData = if(cursor.getInt(4)==VehicleType.BUS.number){
                    returnLineFromCursor(cursor,VehicleType.BUS)
                }else{
                    returnLineFromCursor(cursor,VehicleType.TRAM)
                }
                linedatas.add(lineData)


            }while(cursor.moveToNext())

        }
        cursor.close()
        return  linedatas


    }


    override fun getAllLineWithAnyVehicleStopFitPattern(db: SQLiteDatabase, nameVehicleStop: String): ArrayList<LineData> {

        val baseQuery="SELECT  Line.idLine,Line.numberLine,Line.firstStop,Line.lastStop,VehicleStop.vehicleType, VehicleStop.name from Line join VehicleStopSequence on VehicleStopSequence.idLine=Line.idLine join VehicleStop on VehicleStop.idStopPoint=VehicleStopSequence.idVehicleStop where lower(name) like \"${nameVehicleStop.lowercase()}%\""

        val cursor = db.rawQuery(baseQuery, null
        )


        val linedatas:ArrayList<LineData> = ArrayList()

        if (cursor!!.moveToFirst()) {

            do {
                var lineData:LineData

                lineData = if(cursor.getInt(4)==VehicleType.BUS.number){
                    returnLineFromCursor(cursor,VehicleType.BUS,cursor.getString(5))
                }else{
                    returnLineFromCursor(cursor,VehicleType.TRAM,cursor.getString(5))
                }
                linedatas.add(lineData)


            }while(cursor.moveToNext())

        }
        cursor.close()
        return  linedatas

    }
}