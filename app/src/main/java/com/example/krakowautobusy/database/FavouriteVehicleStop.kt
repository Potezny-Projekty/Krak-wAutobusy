package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.krakowautobusy.api.Api
import com.example.krakowautobusy.database.VehicleStop
class FavouriteVehicleStop :FavouriteVehicleStopInterface {

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


        return   VehicleStopData(
            cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_VEHICLE_STOP_COL]!!),
            cursor.getString(COLUMN_VEHICLE_STOP_TABLE_NUMBER[NAME_COL]!!).toString(),
            cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[LONGITUDE_COL]!!),
            cursor.getLong(COLUMN_VEHICLE_STOP_TABLE_NUMBER[LATITUDE_COL]!!),
            cursor.getInt(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_SHORT_COL]!!),
            vehicleType,
            cursor.getInt(COLUMN_VEHICLE_STOP_TABLE_NUMBER[ID_STOP_POINT_COL]!!),
            if(cursor.getString(0)==null) false else true
        )

    }










    override fun getAllFavouriteVehicleStop(db: SQLiteDatabase): ArrayList<VehicleStopData> {
        val allVehicleStop = arrayListOf<VehicleStopData>()



        val cursor=  db.rawQuery("SELECT  FavouriteVehicleStop."+ID_VEHICLE_STOP_COL+","+ NAME_COL+","+ LONGITUDE_COL+"," +
                LATITUDE_COL+","+ ID_SHORT_COL+","+ VEHICLE_TYPE_COL+","+ ID_STOP_POINT_COL+
                " FROM "+TABLE_VEHICLE_STOP+"  JOIN "+TableName.FAVOURITE_VEHICLE_STOP.nameTable+" ON "+
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

    override fun isVehicleStopFavourite(db: SQLiteDatabase, nameVehicleStop: String): Boolean {
        val queryIsVehicleStopFavourite="SELECT * FROM ${TableName.FAVOURITE_VEHICLE_STOP.nameTable} JOIN ${TableName.VEHICLE_STOP.nameTable}" +
                " ON ${VehicleStopTable.ID_VEHICLE_STOP.nameColumn}=${FavouriteVehicleStops.ID_VEHICLE_STOP.nameColumn} WHERE "

        val filterCondition = "${VehicleStopTable.NAME .nameColumn}='${nameVehicleStop}'"

        val cursor = db.rawQuery(queryIsVehicleStopFavourite+filterCondition,null)




        return if(cursor.count> TABLE_NO_ELEMENT){
            cursor.close()
            Log.e("kursor","prawda")
            true
        }else {
            cursor.close()
            Log.e("kursor","falsz")
            false
        }
    }

    override fun addVehicleStopToFavorite(db: SQLiteDatabase, nameVehicleStop: String) {

        val idVehicleStopsAboutName=Api.getApi().getVehicleStopIdByName(nameVehicleStop) //Api.getApi().getV

        if(!isVehicleStopFavouriteById(db, idVehicleStopsAboutName)){
            val vehicleStop = ContentValues().apply {
                put(FavouriteVehicleStops.ID_VEHICLE_STOP .nameColumn,idVehicleStopsAboutName)
            }
            db.insert(TableName.FAVOURITE_LINE.nameTable,null,vehicleStop)
        }
    }

    override fun removeVehicleStopFromFavourite(db: SQLiteDatabase, nameVehicleStop: String) {

        val idVehicleStop=Api.getApi().getVehicleStopIdByName(nameVehicleStop)
        removeVehicleStopFromFavouriteById(db,idVehicleStop)
    }

    override fun isVehicleStopFavouriteById(db: SQLiteDatabase, IdVehicleStop: String): Boolean {
        val queryIsVehicleStopFavourite="SELECT * FROM ${TableName.FAVOURITE_VEHICLE_STOP.nameTable} JOIN ${TableName.VEHICLE_STOP.nameTable}" +
                " ON ${VehicleStopTable.ID_VEHICLE_STOP.nameColumn}=${FavouriteVehicleStops.ID_VEHICLE_STOP.nameColumn} WHERE "

        val filterCondition = "${VehicleStopTable.ID_VEHICLE_STOP.nameColumn}=${IdVehicleStop}"
        Log.e("vvvv",queryIsVehicleStopFavourite+filterCondition)
        val cursor = db.rawQuery(queryIsVehicleStopFavourite+filterCondition,null)




        return if(cursor.count> TABLE_NO_ELEMENT){
            cursor.close()
            true
        }else {
            cursor.close()
            false
        }
    }

    override fun addVehicleStopToFavoriteById(db: SQLiteDatabase, IdVehicleStop: String) {
        if(!isVehicleStopFavouriteById(db, IdVehicleStop)){
            val vehicleStop = ContentValues().apply {
                put( "IdVehicleStop",IdVehicleStop)
            }
            db.insert(TableName.FAVOURITE_VEHICLE_STOP.nameTable,null,vehicleStop)
        }
        Log.e("kurwap","Insert")
    }

    override fun removeVehicleStopFromFavouriteById(db: SQLiteDatabase, IdVehicleStop: String) {
        val removeCondition = "${FavouriteVehicleStops.ID_VEHICLE_STOP .nameColumn}=?"
        Log.e("kurwap","Delete from FavouriteVehicleStop where idVehicleStop="+IdVehicleStop)
      //  db.rawQuery("Delete from FavouriteVehicleStop where IdVehicleStop="+IdVehicleStop,null)

        db.delete(TableName.FAVOURITE_VEHICLE_STOP.nameTable,  removeCondition, arrayOf( IdVehicleStop))

// val removeCondition = "${FavouriteLineTable.ID_LINE.nameColumn}=?"
//
//        db.delete(TableName.FAVOURITE_LINE.nameTable,  removeCondition, arrayOf( idLine.toString()))
    }

}