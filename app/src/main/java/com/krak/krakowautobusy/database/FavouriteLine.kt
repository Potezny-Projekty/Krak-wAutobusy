package com.krak.krakowautobusy.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.krak.krakowautobusy.api.Api


const val FIRST_COLUMN_RETURN=0
const val FIRST_DIRECTION_POS=0
const val SECOND_DIRECTION_POS=1
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
        val firstStopName= Api.getApi().getNameVehicleStopByID(cursor.getInt(LineTable.FIRST_STOP_ID.indexColumn))
        val lastStopName=Api.getApi().getNameVehicleStopByID(cursor.getInt(LineTable.LAST_STOP_ID.indexColumn))
        return   FavouriteLineData(
            cursor.getInt(LineTable.ID_LINE.indexColumn),
            cursor.getInt(LineTable.NUMBER_LINE.indexColumn),
            firstStopName,
            lastStopName,
            vehicleType
        )

    }



    private fun addLineToFavouriteById(db: SQLiteDatabase, idLine: Int) {

      if(!isLineFavouriteById(db, idLine.toLong())){
          val line = ContentValues().apply {
              put(FavouriteLineTable.ID_LINE.nameColumn,idLine)
          }
          db.insert(TableName.FAVOURITE_LINE.nameTable,null,line)
      }
    }


    /**
     * Add line to favourite for two direction in one line. This is buisness goal
     */
    override fun addLineToFavorite(db: SQLiteDatabase, numberLine: Int) {

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


            if(linesIdBothDirection.size>1) {
                addLineToFavouriteById(db, linesIdBothDirection[FIRST_DIRECTION_POS])
                addLineToFavouriteById(db, linesIdBothDirection[SECOND_DIRECTION_POS])
            }
            }


    }

    override fun removeLineFromFavourite(db: SQLiteDatabase, numberLine: Int) {


        Log.e("favourite","Remove from favourite")

        val linesIdBothDirection  = arrayListOf<Int>()

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

    private fun removeLineFromFavouriteById(db:SQLiteDatabase, idLine: Int) {

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


   private fun isLineFavouriteById(db: SQLiteDatabase, idLine: Long):Boolean {
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