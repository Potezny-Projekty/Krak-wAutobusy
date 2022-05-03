package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class FavouriteLine:FavouriteLineInterface {

    private fun checkTypeVehicleByNumber(numberVehicle:Int):VehicleType{
        return if(numberVehicle==VehicleType.BUS.number){
            VehicleType.BUS
        }else{
            VehicleType.TRAM
        }

    }

    private fun returnFavouriteLineDataFromCursor(database: SQLiteDatabase,
        cursor: Cursor

    ): FavouriteLineData {

        val vehicleType = checkTypeVehicleByNumber(cursor.getInt(LineTable.ID_VEHICLE.indexColumn))

        val firstStopName=findNameBusStopById(database,cursor.getInt(LineTable.FIRST_STOP_ID.indexColumn))
        Log.e("testbaza",";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")
        Log.e("testbaza",cursor.getInt(LineTable.FIRST_STOP_ID.indexColumn).toString()+";")
        Log.e("testbaza",cursor.getInt(LineTable.LAST_STOP_ID.indexColumn).toString()+";")
        Log.e("testbaza",";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")
        val lastStopName=findNameBusStopById(database,cursor.getInt(LineTable.LAST_STOP_ID.indexColumn))

        return   FavouriteLineData(
            cursor.getInt(LineTable.ID_LINE.indexColumn),
            cursor.getInt(LineTable.NUMBER_LINE.indexColumn),
            firstStopName,
            lastStopName,
            vehicleType
        )

    }



    private fun findNameBusStopById( db: SQLiteDatabase,idStop:Int):String{
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


    private fun addLineToFavouriteById(db: SQLiteDatabase, idLine: Int) {

      if(!isLineFavouriteById(db, idLine.toLong())){
          val line = ContentValues().apply {
              put(FavouriteLineTable.ID_LINE.nameColumn,idLine)
          }
          db.insert(TableName.FAVOURITE_LINE.nameTable,null,line)
      }
    }

    override fun addLineToFavorite(db: SQLiteDatabase, numberLine: Int) {
        val FIRST_COLUMN_RETURN=0
        val FIRST_DIRECTION_POS=0
        val SECOND_DIRECTION_POS=1
        if(!isLineFavourite(db,numberLine)){
            val linesIdBothDirection = arrayListOf<Int>()

            val columnReturns = arrayOf(
                LineTable.ID_LINE.nameColumn
            )

            val filterCondition = "${LineTable.NUMBER_LINE.nameColumn}=?"

            val cursor = db.query(
                TableName.LINE.nameTable,
                columnReturns,
                filterCondition,
                arrayOf(numberLine.toString()),
                null,
                null,
                null,
                null
            )
            if (cursor!!.moveToFirst()) {
                do {
                    linesIdBothDirection.add( cursor.getInt(FIRST_COLUMN_RETURN))
                } while (cursor.moveToNext())
            }
            cursor.close()

            Log.e("bazatest",linesIdBothDirection[FIRST_DIRECTION_POS].toString()+" ..")
            Log.e("bazatest",linesIdBothDirection[SECOND_DIRECTION_POS].toString()+ "..")
            addLineToFavouriteById(db,linesIdBothDirection[FIRST_DIRECTION_POS])
            addLineToFavouriteById(db,linesIdBothDirection[SECOND_DIRECTION_POS])
        }


    }

    override fun removeLineFromFavourite(db: SQLiteDatabase, numberLine: Int) {
        var linesIdBothDirection  = arrayListOf<Int>()
        val FIRST_COLUMN_RETURN=0
        val FIRST_DIRECTION_POS=0
        val SECOND_DIRECTION_POS=1

        val columnReturns = arrayOf(
            LineTable.ID_LINE.nameColumn
        )

        val filterCondition = "${LineTable.NUMBER_LINE.nameColumn}=?"

        val cursor = db.query(
            TableName.LINE.nameTable,
            columnReturns,
            filterCondition,
            arrayOf(numberLine.toString()),
            null,
            null,
            null,
            null
        )
        if (cursor!!.moveToFirst()) {
            do {
                linesIdBothDirection.add( cursor.getInt(FIRST_COLUMN_RETURN))
            } while (cursor.moveToNext())
        }
        cursor.close()
        removeLineFromFavouriteById(db,linesIdBothDirection[FIRST_DIRECTION_POS])
        removeLineFromFavouriteById(db,linesIdBothDirection[SECOND_DIRECTION_POS])

    }

    fun removeLineFromFavouriteById(db:SQLiteDatabase, idLine: Int) {

        val removeCondition = "${FavouriteLineTable.ID_LINE.nameColumn}=?"

        db.delete(TableName.FAVOURITE_LINE.nameTable,  removeCondition, arrayOf( idLine.toString()))
    }


    override fun getAllFavouriteLine(db: SQLiteDatabase): ArrayList<FavouriteLineData>  {//to inne dane ma zwracać te które nas interesują
        val favouritesLines = arrayListOf<FavouriteLineData>()

        val getAllFavouriteLineQuery="SELECT ${LineTable.ID_LINE.nameColumn},${LineTable.NUMBER_LINE.nameColumn},${LineTable.FIRST_STOP_ID.nameColumn}," +
                "${LineTable.LAST_STOP_ID.nameColumn},${LineTable.ID_VEHICLE.nameColumn} FROM ${TableName.FAVOURITE_LINE.nameTable} JOIN ${TableName.LINE.nameTable}" +
                " ON ${LineTable.ID_LINE.nameColumn}=FavouriteLine.${FavouriteLineTable.ID_LINE.nameColumn}"

        val cursor = db.rawQuery(getAllFavouriteLineQuery,null)
        if (cursor!!.moveToFirst()) {
            do {
                val favouriteLine: FavouriteLineData =returnFavouriteLineDataFromCursor(db,cursor)
                favouritesLines.add(favouriteLine)

            } while (cursor.moveToNext())
        }
        cursor.close()

        return favouritesLines

    }

    override fun isLineFavourite(db: SQLiteDatabase, numberLine: Int):Boolean {
        val queryIsLineFavourite="SELECT * FROM ${TableName.FAVOURITE_LINE.nameTable} JOIN ${TableName.LINE.nameTable}" +
                " ON ${LineTable.ID_LINE.nameColumn}=FavouriteLine.${FavouriteLineTable.ID_LINE.nameColumn} WHERE "

        val filterCondition = "${LineTable.NUMBER_LINE.nameColumn}=${numberLine}"

        val cursor = db.rawQuery(queryIsLineFavourite+filterCondition,null)




        return if(cursor.count> TABLE_NO_ELEMENT){
            cursor.close()
            true
        }else {
            cursor.close()
            false
        }
    }

    private fun deleteAllFromFavouriteLine(db:SQLiteDatabase){
        db.delete(TableName.FAVOURITE_LINE.nameTable,  null, null)
    }


   fun isLineFavouriteById(db: SQLiteDatabase, idLine: Long):Boolean {
        val columnReturns = arrayOf(
           FavouriteLineTable.ID_FAVOURITE_LINE.nameColumn,FavouriteLineTable.ID_LINE.nameColumn
        )

        val filterCondition = "${FavouriteLineTable.ID_LINE.nameColumn}=?"

        val cursor = db.query(
            TableName.FAVOURITE_LINE.nameTable,
            columnReturns,
            filterCondition,
            arrayOf(idLine.toString()),
            null,
            null,
            null,
            null
        )


        return if(cursor.count> TABLE_NO_ELEMENT){
            cursor.close()
            true
        }else {

            cursor.close()
            false
        }


    }
}