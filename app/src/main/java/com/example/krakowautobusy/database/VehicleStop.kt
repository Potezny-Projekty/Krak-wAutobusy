package com.example.krakowautobusy.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

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


    fun returnVehicleStopPointFromCursor(
        cursor: Cursor,
        vehicle: Vehicle
    ): VehicleStopData {

         return   VehicleStopData(
                cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_VEHICLE_STOP_COL]!!),
                cursor.getString(COLUMN_VEHICLE_STOP_TABLE_NUMBER[NAME_COL]!!).toString(),
                cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[LONGITUDE_COL]!!),
                cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[LATITUDE_COL]!!),
                cursor.getInt(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_SHORT_COL]!!),
                vehicle,
                cursor.getInt(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_STOP_POINT_COL]!!)
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


        val cursor = db.query(
            TABLE_VEHICLE_STOP,
            columnReturns,
            filterCondition,
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        if (cursor!!.moveToFirst()) {

                val findVehicleStop: VehicleStopData =
                    if (COLUMN_VEHICLE_STOP_TABLE_NUMBER[VEHICLE_TYPE_COL]?.let { cursor.getInt(it) } == Vehicle.BUS.number) {
                        returnVehicleStopPointFromCursor(cursor,Vehicle.BUS)
                    } else {
                        returnVehicleStopPointFromCursor(cursor,Vehicle.TRAM)
                    }
                busStop.add(findVehicleStop)

        }
        cursor.close()

        return busStop[0]

    }


    override fun getAllVehicleStop(db: SQLiteDatabase): ArrayList<VehicleStopData> {
        val allVehicleStop = arrayListOf<VehicleStopData>()

        val columnReturns = arrayOf(
            ID_VEHICLE_STOP_COL, NAME_COL, LONGITUDE_COL,
            LATITUDE_COL, ID_SHORT_COL, VEHICLE_TYPE_COL, ID_STOP_POINT_COL
        )

        val cursor = db.query(TABLE_VEHICLE_STOP, columnReturns, null, null, null, null, null, null)
        if (cursor!!.moveToFirst()) {
            do {
                val vehicleStop: VehicleStopData =
                    if (COLUMN_VEHICLE_STOP_TABLE_NUMBER[VEHICLE_TYPE_COL]?.let { cursor.getInt(it) } == Vehicle.BUS.number) {
                        returnVehicleStopPointFromCursor(cursor,Vehicle.BUS)
                    } else {
                        returnVehicleStopPointFromCursor(cursor,Vehicle.TRAM)
                    }
                allVehicleStop.add(vehicleStop)


            } while (cursor.moveToNext())
        }
        cursor.close()
        return allVehicleStop
    }
}