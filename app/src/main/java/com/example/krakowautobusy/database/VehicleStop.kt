package com.example.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class VehicleStop:VehicleStopInterface {

    val ID_VEHICLE_STOP_COL = "idVehicleStop"
    val NAME_COL = "name"
    val LONGITUDE_COL = "longtitude"
    val LATITUDE_COL = "lattitude"
    val ID_SHORT_COL = "idShort"
    val VEHICLE_TYPE_COL = "vehicleType"
    val ID_STOP_POINT_COL = "idStopPoint"
    val TABLE_VEHICLE_STOP = "VehicleStop"

    val COLUMN_VEHICLE_STOP_TABLE_NUMBER = LinkedHashMap<String, Int>()

    init {
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_VEHICLE_STOP_COL] = 0
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[NAME_COL] = 1
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[LONGITUDE_COL] = 2
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[LATITUDE_COL] = 3
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_SHORT_COL] = 4
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[VEHICLE_TYPE_COL] = 5
        COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_STOP_POINT_COL] = 6
    }


  public  fun returnVehicleStopPointFromCursor(
        cursor: Cursor,
        vehicleType: VehicleType
    ): VehicleStopData {

        Log.e("ope",cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_VEHICLE_STOP_COL]!!).toString())
         return   VehicleStopData(
                cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_VEHICLE_STOP_COL]!!),
                cursor.getString(COLUMN_VEHICLE_STOP_TABLE_NUMBER[NAME_COL]!!).toString(),
                cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[LONGITUDE_COL]!!),
                cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[LATITUDE_COL]!!),
                cursor.getInt(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_SHORT_COL]!!),
                vehicleType,
                cursor.getInt(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_STOP_POINT_COL]!!),
                if(cursor.getString(7)==null) false else true
            )

    }


    override fun getVehicleStopsByID(
        db: SQLiteDatabase,
        id: Long
    ): VehicleStopData {
        var busStop = arrayListOf<VehicleStopData>()


        val columnReturns = arrayOf(
            ID_VEHICLE_STOP_COL, NAME_COL, LONGITUDE_COL,
            LATITUDE_COL, ID_SHORT_COL, VEHICLE_TYPE_COL, ID_STOP_POINT_COL
        )
        val filterCondition = "${ID_STOP_POINT_COL}=?"


        val cursor=  db.rawQuery("SELECT  VehicleStop."+ID_VEHICLE_STOP_COL+","+ NAME_COL+","+ LONGITUDE_COL+"," +
                LATITUDE_COL+","+ ID_SHORT_COL+","+ VEHICLE_TYPE_COL+","+ ID_STOP_POINT_COL+",FavouriteVehicleStop.IdVehicleStop"+
                " FROM "+TABLE_VEHICLE_STOP+" LEFT OUTER JOIN "+TableName.FAVOURITE_VEHICLE_STOP.nameTable+" ON "+
                TABLE_VEHICLE_STOP+".idVehicleStop="
                + TableName.FAVOURITE_VEHICLE_STOP.nameTable+".IdVehicleStop Where "+"${ID_STOP_POINT_COL}="+id.toString(),null
        )

        if (cursor!!.moveToFirst()) {

                val findVehicleStopType: VehicleStopData =
                    if (COLUMN_VEHICLE_STOP_TABLE_NUMBER[VEHICLE_TYPE_COL]?.let { cursor.getInt(it) } == VehicleType.BUS.number) {
                        returnVehicleStopPointFromCursor(cursor,VehicleType.BUS)
                    } else {
                        returnVehicleStopPointFromCursor(cursor,VehicleType.TRAM)
                    }
                busStop.add(findVehicleStopType)

        }
        cursor.close()

        if(busStop.size>0){
            return busStop[0]
        }else{//źle to jest formalnie
            return VehicleStopData(-1,"Brak",0,0,0,VehicleType.BUS,0,false);
        }


    }


    override fun getAllVehicleStop(db: SQLiteDatabase): ArrayList<VehicleStopData> {
        val allVehicleStop = arrayListOf<VehicleStopData>()

        val columnReturns = arrayOf(
            ID_VEHICLE_STOP_COL, NAME_COL, LONGITUDE_COL,
            LATITUDE_COL, ID_SHORT_COL, VEHICLE_TYPE_COL, ID_STOP_POINT_COL
        )

        val cursor=  db.rawQuery("SELECT  VehicleStop."+ID_VEHICLE_STOP_COL+","+ NAME_COL+","+ LONGITUDE_COL+"," +
                            LATITUDE_COL+","+ ID_SHORT_COL+","+ VEHICLE_TYPE_COL+","+ ID_STOP_POINT_COL+",FavouriteVehicleStop.IdVehicleStop"+
                " FROM "+TABLE_VEHICLE_STOP+" LEFT OUTER JOIN "+TableName.FAVOURITE_VEHICLE_STOP.nameTable+" ON "+
                TABLE_VEHICLE_STOP+".idVehicleStop="
                + TableName.FAVOURITE_VEHICLE_STOP.nameTable+".IdVehicleStop",null
        )







        //val cursor = db.query(TABLE_VEHICLE_STOP, columnReturns, null, null, null, null, null, null)
        if (cursor!!.moveToFirst()) {
            do {
                val vehicleTypeStop: VehicleStopData =
                    if (COLUMN_VEHICLE_STOP_TABLE_NUMBER[VEHICLE_TYPE_COL]?.let { cursor.getInt(it) } == VehicleType.BUS.number) {
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

    override fun getAllVehicleStopLine(db: SQLiteDatabase): ArrayList<VehicleStopData> {



        TODO("Not yet implemented")
    }

    override fun findNameBusStopById(db: SQLiteDatabase, idStop: Int): String {
        val columnReturns = arrayOf(VehicleStopTable.NAME.nameColumn)
        val FIRST_COLUMN_RETURN=0
        val RETURN_NOTHING=""

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

            return   cursor.getString(FIRST_COLUMN_RETURN)
        }
        return RETURN_NOTHING
    }

    override fun getVehicleStopIdByName(db: SQLiteDatabase, nameVehicleStop: String): String {
        val columnReturns = arrayOf(VehicleStopTable.ID_STOP_POINT.nameColumn)
        val FIRST_COLUMN_RETURN=0
        val RETURN_NOTHING=""

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

            return   cursor.getString(FIRST_COLUMN_RETURN)
        }
        return RETURN_NOTHING
    }
}