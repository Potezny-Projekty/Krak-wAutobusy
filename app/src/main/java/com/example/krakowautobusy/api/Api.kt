package com.example.krakowautobusy.api

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.krakowautobusy.database.*
import com.example.krakowautobusy.networkttss.PositionVehicle
import com.example.krakowautobusy.networkttss.PositionVehicleInterface
import com.example.krakowautobusy.networkttss.TimeTableVehicle
import com.example.krakowautobusy.networkttss.TimeTableVehicleInterface
import com.example.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.example.krakowautobusy.ui.map.vehicledata.TimeTableData
import com.google.gson.JsonObject
import retrofit2.Response

class Api {
    private val vehicleStopAccess: VehicleStopInterface = VehicleStop()
    private val favouriteLineAccess: FavouriteLineInterface = FavouriteLine()
    private val positionVehicleAccess: PositionVehicleInterface = PositionVehicle()
    private val timeTableVehicleAcess: TimeTableVehicleInterface = TimeTableVehicle()
    private val lineAccess:LineInteerface=Line()
    private val FavouritevehicleStopAcess:FavouriteVehicleStopInterface=FavouriteVehicleStop()
    private var context: Context
    private var database: Database


  private  constructor(context: Context) {
        this.context = context
        database = Database.getInstance(context)


    }




    companion object {

        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: Api? = null

        fun getApi():Api{
            if(INSTANCE==null){
                //wyjatek?
            }
                return INSTANCE !!

        }


         fun buildApi(context: Context) {
            INSTANCE=Api(context)
        }

    }


    /////////////////////////Favourite VehicleStop

    fun getAllFavouriteVehicleStop():ArrayList<VehicleStopData>{
        return FavouritevehicleStopAcess.getAllFavouriteVehicleStop(database.readableDatabase)
    }

    fun isVehicleStopFavourite( nameVehicleStop: String):Boolean{
        return FavouritevehicleStopAcess.isVehicleStopFavourite(database.readableDatabase,nameVehicleStop)
    }

    fun addVehicleStopToFavorite( nameVehicleStop: String){
        return FavouritevehicleStopAcess.addVehicleStopToFavorite(database.writableDatabase,nameVehicleStop)
    }

    fun removeVehicleStopFromFavourite( nameVehicleStop: String){
        return FavouritevehicleStopAcess.removeVehicleStopFromFavourite(database.writableDatabase,nameVehicleStop)
    }


    fun isVehicleStopFavouriteById(IdVehicleStop: String):Boolean{
        return FavouritevehicleStopAcess.isVehicleStopFavouriteById(database.readableDatabase,IdVehicleStop)
    }

    fun addVehicleStopToFavoriteById( IdVehicleStop: String){
        return FavouritevehicleStopAcess.addVehicleStopToFavoriteById(database.writableDatabase,IdVehicleStop)
    }

    fun removeVehicleStopFromFavouriteById( IdVehicleStop: String){
        return FavouritevehicleStopAcess.removeVehicleStopFromFavouriteById(database.writableDatabase,IdVehicleStop)
    }






 //////////////////////Line API

  fun  getInfoAboutLinePatternNumber( patternNumber: Int): ArrayList<LineData>   {
     return lineAccess.getInfoAboutLinePatternNumber(database.readableDatabase,patternNumber );
  }
fun getInfoAboutLinePatternAnyVehicleStop(patternName:String):ArrayList<LineData>{
    return lineAccess.getAllLineWithAnyVehicleStopFitPattern(database.readableDatabase,patternName)
}


fun getAllLine():ArrayList<LineData>{
    return lineAccess.getAllLine(database.readableDatabase)
}



///////////////////  Position Vehicle API






    fun getInfoAboutLineConcretDirectionLastStopID(

        numberLine: Long,
        lastStopId: Long
    ): LineData {

      return   lineAccess.getInfoAboutLineConcretDirectionId(database.readableDatabase,numberLine,lastStopId)

    }



    fun getInfoAboutLineConcretDirectionLastStopName(

        numberLine: Int,
        lastStopName: String
    ): LineData {

        return   lineAccess.getInfoAboutLineConcretDirectionName(database.readableDatabase,numberLine,lastStopName)

    }


    public fun getTramPath(
        idTram: String,
        combine: (Response<JsonObject>) -> Unit
    ) {
        positionVehicleAccess.getTramPath(idTram,combine)
    }



    public fun getBusPath(
        idBus: String,
        combine: (Response<JsonObject>) -> Unit
    ) {
        positionVehicleAccess.getBusPath(idBus,combine)
    }


    public fun getTimeTableBus(tripId:String,
                               vehicleId:String,
                               combine:(Response<TimeTableData>)->Unit){
        timeTableVehicleAcess.getBusVehicleTimeTable(vehicleId,tripId,combine)
    }

    public fun getTimeTableTram(tripId:String,
                               vehicleId:String,
                               combine:(Response<TimeTableData>)->Unit){
        timeTableVehicleAcess.getTramVehicleTimeTable(vehicleId,tripId,combine)
    }


    public fun getTramPosition(
        lastUpdate: Long,
        combine: (Response<AllVehicles>) -> Unit
    ) {
        positionVehicleAccess.getTramPosition(lastUpdate, combine)
    }





    public fun getBusPosition(
        lastUpdate: Long,
        combine: (Response<AllVehicles>) -> Unit
    ) {
        positionVehicleAccess.getBusPosition(lastUpdate, combine)
    }


    /////// Vehicle Stop API
    public fun getAllVehiclesStop(): ArrayList<VehicleStopData> {
        return vehicleStopAccess.getAllVehicleStop(database.readableDatabase)
    }


    public fun getVehicleStopLines(numberLine: Int):ArrayList<SequenceVehicleStopData>{
        return lineAccess.getVehicleStopsLine(database.readableDatabase,numberLine)
    }

    public fun getVehicleStopById(shortId: Long): VehicleStopData {
        return vehicleStopAccess.getVehicleStopsByID(database.readableDatabase, shortId)
    }


    fun getNameVehicleStopByID(idVehicleStop: Int):String{
        return vehicleStopAccess.findNameBusStopById(database.readableDatabase,idVehicleStop);
    }

    fun  getVehicleStopIdByName( nameVehicleStop: String):String{
        return vehicleStopAccess.getVehicleStopIdByName(database.readableDatabase,nameVehicleStop);
    }

    ///////Favourite Line API
  //  fun isLineFavourite(idLine: Long): Boolean {
   //     return favouriteLineAccess.isLineFavourite(database.readableDatabase, idLine)
  //  }

    fun isLineFavourite(numberLine:Int):Boolean{
        return favouriteLineAccess.isLineFavourite(database.readableDatabase,numberLine)
    }

    fun removeLinesFromFavourites(numberLine:Int){
        favouriteLineAccess.removeLineFromFavourite(database.writableDatabase,numberLine)
    }

    fun getAllFavouriteLine(): ArrayList<FavouriteLineData> {
        return favouriteLineAccess.getAllFavouriteLine(database.readableDatabase)
    }

   // fun addLineToFavourite(idLine: Int) {
   //     favouriteLineAccess.addLineToFavourite(database.writableDatabase, idLine)
   // }

    fun addLineToFavourite(nameLine:Int){
        favouriteLineAccess.addLineToFavorite(database.writableDatabase,nameLine)
    }

  //  fun removeLineFromFavourite(idLine: Int){
  //       favouriteLineAccess.removeLineFromFavourite(database.writableDatabase, idLine)
  //  }

}