package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class FavouriteLine:FavouriteLineInterface {

    val ID_FAVOURITE = "idFavouriteLine"
    val ID_LINE = "idLine"
    val FAVOURITE_LINE_TABLE_NAME="FavouriteLine"
    val NO_ELEMENT=0

    val COLUMN_FAVOURITE_LINE_TABLE_NUMBER = LinkedHashMap<String, Int>()

    init {
        COLUMN_FAVOURITE_LINE_TABLE_NUMBER[ID_FAVOURITE] = 0
        COLUMN_FAVOURITE_LINE_TABLE_NUMBER[ID_LINE] = 1

    }


    fun returnFavouriteLineDataFromCursor(
        cursor: Cursor

    ): FavouriteLineData {

        return   FavouriteLineData(
            cursor.getInt(COLUMN_FAVOURITE_LINE_TABLE_NUMBER[ID_FAVOURITE]!!),
            cursor.getInt(COLUMN_FAVOURITE_LINE_TABLE_NUMBER[ID_LINE]!!),

        )

    }


    override fun addLineToFavourite(db: SQLiteDatabase, idLine: Int): Boolean {

        val line = ContentValues().apply {

            put(ID_LINE,idLine)
        }

        return db.insert(FAVOURITE_LINE_TABLE_NAME,null,line)>NO_ELEMENT

    }

    override fun removeLineFromFavourite(db:SQLiteDatabase,idLine: Int): Boolean {

        val removeCondition = "${ID_LINE}=?"

        return db.delete(FAVOURITE_LINE_TABLE_NAME,  removeCondition, arrayOf( idLine.toString())) > NO_ELEMENT;
    }


    override fun getAllFavouriteLine(db: SQLiteDatabase): ArrayList<FavouriteLineData>  {
        var favouritesLine = arrayListOf<FavouriteLineData>()


        val columnReturns = arrayOf(
            ID_FAVOURITE,ID_LINE
        )

        val cursor = db.query(
            FAVOURITE_LINE_TABLE_NAME,
            columnReturns,
            null,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor!!.moveToFirst()) {
            do {
                val favouriteLine: FavouriteLineData =returnFavouriteLineDataFromCursor(cursor)

                favouritesLine.add(favouriteLine)


            } while (cursor.moveToNext())
        }
        cursor.close()

        return favouritesLine

    }


    override fun isLineFavourite(db: SQLiteDatabase, idLine: Long):Boolean {


        val columnReturns = arrayOf(
            ID_FAVOURITE,ID_LINE
        )

        val filterCondition = "${ID_LINE}=?"


        val cursor = db.query(
            FAVOURITE_LINE_TABLE_NAME,
            columnReturns,
            filterCondition,
            arrayOf(idLine.toString()),
            null,
            null,
            null,
            null
        )


        return if(cursor.count>NO_ELEMENT){
            cursor.close()
            true
        }else {

            cursor.close()
            false
        }


    }
}