package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class FavouriteLine:FavouriteLineInterface {

    fun returnFavouriteLineDataFromCursor(
        cursor: Cursor

    ): FavouriteLineData {



        return   FavouriteLineData(
            cursor.getInt(FavouriteLineTable.ID_FAVOURITE_LINE.indexColumn),
            cursor.getInt(FavouriteLineTable.ID_LINE.indexColumn),

        )

    }


    fun addLineToFavouriteById(db: SQLiteDatabase, idLine: Int) {

      val isLineAlreadyFavourites=  isLineFavouriteById(db, idLine.toLong())
      if(!isLineAlreadyFavourites){
          val line = ContentValues().apply {
              put(FavouriteLineTable.ID_FAVOURITE_LINE.nameColumn,idLine)
          }

          db.insert(TableName.FAVOURITE_LINE_TABLE_NAME.nameTable,null,line)
      }
    }

    override fun addLineToFavoriteNumberLine(db: SQLiteDatabase, numberLine: Int) {
        //select Line.idLine from Line where Line.numberLine=537

        val isLineAlreadyFavourite=isLineFavourite(db,numberLine)

        if(!isLineAlreadyFavourite){
            var linesId = arrayListOf<Int>()


            val columnReturns = arrayOf(
                "Line.idLine"
            )

            val filterCondition = "Line.numberLine=?"

            val cursor = db.query(
                "Line",
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


                    linesId.add( cursor.getInt(0))


                } while (cursor.moveToNext())
            }
            cursor.close()
            Log.e("testbaza","xxx"+linesId[0].toString()+" "+linesId[1].toString())

            addLineToFavouriteById(db,linesId[0]!!)
            addLineToFavouriteById(db,linesId[1]!!)
        }


    }

    override fun removeLineFromFavourite(db: SQLiteDatabase, numberLine: Int) {
        var linesId = arrayListOf<Int>()


        val columnReturns = arrayOf(
            "Line.idLine"
        )

        val filterCondition = "Line.numberLine=?"

        val cursor = db.query(
            "Line",
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


                linesId.add( cursor.getInt(0))


            } while (cursor.moveToNext())
        }
        cursor.close()
        removeLineFromFavouriteById(db,linesId[0])
        removeLineFromFavouriteById(db,linesId[1])

    }

    fun removeLineFromFavouriteById(db:SQLiteDatabase, idLine: Int) {

        val removeCondition = "${FavouriteLineTable.ID_LINE.nameColumn}=?"

        db.delete(TableName.FAVOURITE_LINE_TABLE_NAME.nameTable,  removeCondition, arrayOf( idLine.toString()))
    }


    override fun getAllFavouriteLine(db: SQLiteDatabase): ArrayList<FavouriteLineData>  {//to inne dane ma zwracać te które nas interesują
        var favouritesLine = arrayListOf<FavouriteLineData>()


        val columnReturns = arrayOf(
            FavouriteLineTable.ID_FAVOURITE_LINE.nameColumn,FavouriteLineTable.ID_LINE.nameColumn
        )

        val cursor = db.query(
            TableName.FAVOURITE_LINE_TABLE_NAME.nameTable,
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

    override fun isLineFavourite(db: SQLiteDatabase, numberLine: Int):Boolean {
        val basequey="select * from FavouriteLine join Line on Line.idLine=FavouriteLine.idLine where "

        val filterCondition = "Line.numberLine=${numberLine}"

        val cursor = db.rawQuery(basequey+filterCondition,null
        )


        return if(cursor.count> TABLE_NO_ELEMENT){
            cursor.close()
            true
        }else {

            cursor.close()
            false
        }
    }


   fun isLineFavouriteById(db: SQLiteDatabase, idLine: Long):Boolean {
        val columnReturns = arrayOf(
           FavouriteLineTable.ID_FAVOURITE_LINE.nameColumn,FavouriteLineTable.ID_LINE.nameColumn
        )

        val filterCondition = "${FavouriteLineTable.ID_LINE.nameColumn}=?"

        val cursor = db.query(
            TableName.FAVOURITE_LINE_TABLE_NAME.nameTable,
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