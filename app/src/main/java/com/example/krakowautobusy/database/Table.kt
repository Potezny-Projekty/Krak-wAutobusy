package com.example.krakowautobusy.database

enum class FavouriteLineTable(val nameColumn: String,val indexColumn:Int) {
    ID_FAVOURITE_LINE("FavouriteLine.idFavouriteLine",0),
    ID_LINE("FavouriteLine.idLine",1)
}


enum class TableName(val nameTable:String){
    FAVOURITE_LINE_TABLE_NAME("FavouriteLine")
}

val TABLE_NO_ELEMENT=0


