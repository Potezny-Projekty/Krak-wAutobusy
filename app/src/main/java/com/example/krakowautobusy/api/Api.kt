package com.example.krakowautobusy.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.krakowautobusy.database.*

class Api {
    private  val vehicleStopAccess:VehicleStopInterface=VehicleStop()
    private val favouriteLineAccess:FavouriteLineInterface=FavouriteLine()
    private var context:Context
    private var database:Database


    constructor(context: Context){
        this.context=context
        database = Database.getInstance(context)

    }












/////// Vehicle Stop API
    public fun  getAllVehiclesStop(): ArrayList<VehicleStopData> {
        return vehicleStopAccess.getAllVehicleStop(database.readableDatabase)
    }

    public fun  getVehicleStopById( shortId:Long): VehicleStopData {
        return vehicleStopAccess.getVehicleStopsByID(database.readableDatabase,shortId)
    }

///////Favourite Line API
    fun isLineFavourite(db: SQLiteDatabase, idLine:Long):Boolean{
        return favouriteLineAccess.isLineFavourite(db,idLine)
    }

    fun getAllFavouriteLine(db: SQLiteDatabase):ArrayList<FavouriteLineData>{
        return favouriteLineAccess.getAllFavouriteLine(db)
    }

    fun addLineToFavourite(db: SQLiteDatabase, idLine:Int):Boolean{
        return favouriteLineAccess.addLineToFavourite(db,idLine)
    }

    fun removeLineFromFavourite(db: SQLiteDatabase, idLine:Int):Boolean{
        return favouriteLineAccess.removeLineFromFavourite(db,idLine)
    }

}