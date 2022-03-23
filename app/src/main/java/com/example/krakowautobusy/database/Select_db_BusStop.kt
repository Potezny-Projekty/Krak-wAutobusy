package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

class Select_db_BusStop:Select_db_BusStopInterface {


    override fun selectBusStop(db: SQLiteDatabase, id:Long):ArrayList<Select_db_BusStopInterface.BusStopRow>{
    var busStop=arrayListOf<Select_db_BusStopInterface.BusStopRow>()

    val TABLE_BUS_STOP = "busStopTable"
    val COLUMNS_RETURN= arrayOf("idBusStop","nameBusStop","longtitude","lattitude","IdShortBusStop","kinOfBusStop","stopPoint")
    val SELECTION_COL="idBusStop=?"


 //   var line = ""

    val cursor = db.query(TABLE_BUS_STOP,COLUMNS_RETURN,SELECTION_COL, arrayOf( id.toString()),null,null,null,null)
    if (cursor!!.moveToFirst()) {
        do {
            var busStopOne:Select_db_BusStopInterface.BusStopRow
            if(cursor.getInt(5)==1){
                 busStopOne=Select_db_BusStopInterface.BusStopRow(cursor.getLong(0),cursor.getString(1).toString(),cursor.getLong(2),cursor.getLong(3),cursor.getInt(4),Select_db_BusStopInterface.Vehicle.BUS,cursor.getInt(6))
            }else{
                busStopOne=Select_db_BusStopInterface.BusStopRow(cursor.getLong(0),cursor.getString(1).toString(),cursor.getLong(2),cursor.getLong(3),cursor.getInt(4),Select_db_BusStopInterface.Vehicle.TRAM,cursor.getInt(6))
            }
            busStop.add(busStopOne)

           // line += cursor.getInt(0).toString()
        } while (cursor.moveToNext())
    }
    cursor.close()
    //db.close()
    return busStop
}


    override fun selectBusStopAll(db: SQLiteDatabase): ArrayList<Select_db_BusStopInterface.BusStopRow> {
        var busStop=arrayListOf<Select_db_BusStopInterface.BusStopRow>()

        val TABLE_BUS_STOP = "busStopTable"
        val COLUMNS_RETURN= arrayOf("idBusStop","nameBusStop","longtitude","lattitude","IdShortBusStop","kinOfBusStop","stopPoint")
        val SELECTION_COL="idBusStop=?"


        //   var line = ""

        val cursor = db.query(TABLE_BUS_STOP,COLUMNS_RETURN,null,null,null,null,null,null)
        if (cursor!!.moveToFirst()) {
            do {
                var busStopOne:Select_db_BusStopInterface.BusStopRow
                if(cursor.getInt(5)==1){
                    busStopOne=Select_db_BusStopInterface.BusStopRow(cursor.getLong(0),cursor.getString(1).toString(),cursor.getLong(2),cursor.getLong(3),cursor.getInt(4),Select_db_BusStopInterface.Vehicle.BUS,cursor.getInt(6))
                }else{
                    busStopOne=Select_db_BusStopInterface.BusStopRow(cursor.getLong(0),cursor.getString(1).toString(),cursor.getLong(2),cursor.getLong(3),cursor.getInt(4),Select_db_BusStopInterface.Vehicle.TRAM,cursor.getInt(6))
                }
                busStop.add(busStopOne)

                // line += cursor.getInt(0).toString()
            } while (cursor.moveToNext())
        }
        cursor.close()
        //db.close()
        return busStop
    }







}