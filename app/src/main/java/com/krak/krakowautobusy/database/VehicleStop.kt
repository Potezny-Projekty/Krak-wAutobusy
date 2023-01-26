package com.krak.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.lang.IllegalStateException

class VehicleStop:VehicleStopInterface {

    private val idVehicleStopCol = "idVehicleStop"
    private val nameCol = "name"
    private val longitudeCol = "longtitude"
    private val latitudeCol = "lattitude"
    private val idShortCol = "idShort"
    private val vehicleTypeCol = "vehicleType"
    private val idStopPointCol = "idStopPoint"
    private val tableVehicleStopCol = "VehicleStop"
    private val isFavouriteCol="isFavourite"
    private val columnVehicleStopTableNumber = LinkedHashMap<String, Int>()

    init {
        columnVehicleStopTableNumber[idVehicleStopCol] = 0
        columnVehicleStopTableNumber[nameCol] = 1
        columnVehicleStopTableNumber[longitudeCol] = 2
        columnVehicleStopTableNumber[latitudeCol] = 3
        columnVehicleStopTableNumber[idShortCol] = 4
        columnVehicleStopTableNumber[vehicleTypeCol] = 5
        columnVehicleStopTableNumber[idStopPointCol] = 6
        columnVehicleStopTableNumber[isFavouriteCol] = 7
    }


    private fun returnVehicleStopPointFromCursor(
        cursor: Cursor,
        vehicleType: VehicleType
    ): VehicleStopData {


         return   VehicleStopData(
                cursor.getLong(columnVehicleStopTableNumber[idVehicleStopCol]!!),
                cursor.getString(columnVehicleStopTableNumber[nameCol]!!).toString(),
                cursor.getLong(columnVehicleStopTableNumber[longitudeCol]!!),
                cursor.getLong(columnVehicleStopTableNumber[latitudeCol]!!),
                cursor.getInt(columnVehicleStopTableNumber[idShortCol]!!),
                vehicleType,
                cursor.getInt(columnVehicleStopTableNumber[idStopPointCol]!!),
             cursor.getString(columnVehicleStopTableNumber[isFavouriteCol]!!) != null
            )

    }


    override fun getVehicleStopsByID(
        db: SQLiteDatabase,
        id: Long
    ): VehicleStopData {
        val busStop = arrayListOf<VehicleStopData>()

        val cursor=  db.rawQuery("SELECT  VehicleStop."+idVehicleStopCol+","+ nameCol+","+ longitudeCol+"," +
                latitudeCol+","+ idShortCol+","+ vehicleTypeCol+","+ idStopPointCol+",FavouriteVehicleStop.IdVehicleStop"+
                " FROM "+tableVehicleStopCol+" LEFT OUTER JOIN "+TableName.FAVOURITE_VEHICLE_STOP.nameTable+" ON "+
                tableVehicleStopCol+".idVehicleStop="
                + TableName.FAVOURITE_VEHICLE_STOP.nameTable+".IdVehicleStop Where "+"${idStopPointCol}="+id.toString(),null
        )

        if (cursor!!.moveToFirst()) {

                val findVehicleStopType: VehicleStopData =
                    if (columnVehicleStopTableNumber[vehicleTypeCol]?.let { cursor.getInt(it) } == VehicleType.BUS.number) {
                        returnVehicleStopPointFromCursor(cursor,VehicleType.BUS)
                    } else {
                        returnVehicleStopPointFromCursor(cursor,VehicleType.TRAM)
                    }
                busStop.add(findVehicleStopType)

        }
        cursor.close()

        return if(busStop.size>0){
            busStop[0]
        }else{
            throw IllegalStateException("This state can't exist")
        }

    }



    override fun getAllVehicleStopAsLineData(db:SQLiteDatabase,name:String):ArrayList<LineData>{
        val finalLineData:ArrayList<LineData> = ArrayList()
        val allvehicles=getAllVehicleStop(db)
        val defaultNumericValue=0L
        val defaultStringValue=""
        for(x in allvehicles){
            if(x.name.lowercase().contains(name.lowercase())) {
                finalLineData.add(
                    LineData(
                        defaultNumericValue,
                        defaultNumericValue,
                        defaultNumericValue,
                        defaultNumericValue,
                        VehicleType.BUS,
                        defaultStringValue,
                        defaultStringValue,
                        x.isFavourite,
                        defaultStringValue,
                        x.name,
                        x.idVehicleStop
                    )
                )
            }
            }
        return finalLineData
    }

    override fun getAllVehicleStop(db: SQLiteDatabase): ArrayList<VehicleStopData> {
        val allVehicleStop = arrayListOf<VehicleStopData>()

        val cursor=  db.rawQuery("SELECT  VehicleStop."+idVehicleStopCol+","+ nameCol+","+ longitudeCol+"," +
                            latitudeCol+","+ idShortCol+","+ vehicleTypeCol+","+ idStopPointCol+",FavouriteVehicleStop.IdVehicleStop"+
                " FROM "+tableVehicleStopCol+" LEFT OUTER JOIN "+TableName.FAVOURITE_VEHICLE_STOP.nameTable+" ON "+
                tableVehicleStopCol+".idVehicleStop="
                + TableName.FAVOURITE_VEHICLE_STOP.nameTable+".IdVehicleStop",null
        )

        if (cursor!!.moveToFirst()) {
            do {
                val vehicleTypeStop: VehicleStopData =
                    if (columnVehicleStopTableNumber[vehicleTypeCol]?.let { cursor.getInt(it) } == VehicleType.BUS.number) {
                        returnVehicleStopPointFromCursor(cursor,VehicleType.BUS)
                    } else {
                        returnVehicleStopPointFromCursor(cursor,VehicleType.TRAM)
                    }
                allVehicleStop.add(vehicleTypeStop)


            } while (cursor.moveToNext())
        }
        cursor.close()
        return allVehicleStop
    }


    /**
     * This method possible implemented in future
     * @suppress no implemented
     */
    override fun getAllVehicleStopLine(db: SQLiteDatabase): ArrayList<VehicleStopData> {
        TODO("Not yet implemented")
    }

    override fun findNameBusStopById(db: SQLiteDatabase, idStop: Int): String {
        val columnReturns = arrayOf(VehicleStopTable.NAME.nameColumn)
        val firstColumnReturn=0
        val returnNothingDefault=""

        val filterCondition = "${VehicleStopTable.ID_STOP_POINT.nameColumn}=?"

        val cursor = db.query(
            TableName.VEHICLE_STOP.nameTable,
            columnReturns,
            filterCondition,
            arrayOf(idStop.toString()),
            null,
            null,
            null,
            null
        )
        if (cursor!!.moveToFirst()) {

            return   cursor.getString(firstColumnReturn)
        }

        cursor.close()
        return returnNothingDefault
    }

    override fun getVehicleStopIdByName(db: SQLiteDatabase, nameVehicleStop: String): String {
        val columnReturns = arrayOf(VehicleStopTable.ID_VEHICLE_STOP.nameColumn)
        val firstColumnReturn=0
        val returnNothingDefault=""
        val filterCondition = "${VehicleStopTable.NAME.nameColumn}=?"

        val cursor = db.query(
            TableName.VEHICLE_STOP.nameTable,
            columnReturns,
            filterCondition,
            arrayOf(nameVehicleStop),
            null,
            null,
            null,
            null
        )
        if (cursor!!.moveToFirst()) {

            return   cursor.getString(firstColumnReturn)
        }

        cursor.close()
        return returnNothingDefault
    }
}