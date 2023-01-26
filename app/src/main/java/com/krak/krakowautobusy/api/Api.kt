package com.krak.krakowautobusy.api

import android.annotation.SuppressLint
import android.content.Context
import com.krak.krakowautobusy.database.*
import com.krak.krakowautobusy.networkttss.*
import com.krak.krakowautobusy.ui.map.vehicledata.AllVehicles
import com.krak.krakowautobusy.ui.map.vehicledata.TimeTableData
import com.google.gson.JsonObject
import retrofit2.Response

/**
 * Class provide global access to inner API, espessially access to database and network request
 */
class Api private constructor(context: Context) {
    private val vehicleStopAccess: VehicleStopInterface = VehicleStop()
    private val favouriteLineAccess: FavouriteLineInterface = FavouriteLine()
    private val positionVehicleAccess: PositionVehicleInterface = PositionVehicle()
    private val timeTableVehicleAcess: TimeTableVehicleInterface = TimeTableVehicle()
    private val departVehicleAccess:DeparturesVehicleInterface=DeparturesVehicles()
    private val lineAccess:LineInteerface=Line()
    private val favouritevehicleStopAcess:FavouriteVehicleStopInterface=FavouriteVehicleStop()
    private var database: Database


    init {
        database = Database.getInstance(context)
    }



    companion object {

        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: Api? = null

        fun getApi():Api{
            if(INSTANCE==null){
                throw UninitializedPropertyAccessException("Use API without initialization")
            }
                return INSTANCE !!
        }


         fun buildApi(context: Context) {
            INSTANCE=Api(context)
        }

    }




    fun getAllVehicleStopAsLineData(name:String):ArrayList<LineData>{
          return vehicleStopAccess.getAllVehicleStopAsLineData(database.readableDatabase,name)
    }

    fun getAllFavouriteVehicleStop():ArrayList<VehicleStopData>{
        return favouritevehicleStopAcess.getAllFavouriteVehicleStop(database.readableDatabase)
    }

    fun isVehicleStopFavourite( nameVehicleStop: String):Boolean{
        return favouritevehicleStopAcess.isVehicleStopFavourite(database.readableDatabase,nameVehicleStop)
    }

    fun addVehicleStopToFavorite( nameVehicleStop: String){
        return favouritevehicleStopAcess.addVehicleStopToFavorite(database.writableDatabase,nameVehicleStop)
    }
    fun addVehicleStopToFavorite( idVehicleStop: Long){
        return favouritevehicleStopAcess.addVehicleStopToFavorite(database.writableDatabase,idVehicleStop)
    }

    fun removeVehicleStopFromFavourite( nameVehicleStop: String){
        return favouritevehicleStopAcess.removeVehicleStopFromFavourite(database.writableDatabase,nameVehicleStop)
    }


    fun isVehicleStopFavouriteById(IdVehicleStop: String):Boolean{
        return favouritevehicleStopAcess.isVehicleStopFavouriteById(database.readableDatabase,IdVehicleStop)
    }

    fun addVehicleStopToFavoriteById( IdVehicleStop: String){
        return favouritevehicleStopAcess.addVehicleStopToFavoriteById(database.writableDatabase,IdVehicleStop)
    }

    fun removeVehicleStopFromFavouriteById( IdVehicleStop: String){
        return favouritevehicleStopAcess.removeVehicleStopFromFavouriteById(database.writableDatabase,IdVehicleStop)
    }


   fun  getInfoAboutLinePatternNumber( patternNumber: Int): ArrayList<LineData>   {
     return lineAccess.getInfoAboutLinePatternNumber(database.readableDatabase,patternNumber )
   }

   fun getInfoAboutLinePatternAnyVehicleStop(patternName:String):ArrayList<LineData>{
    return lineAccess.getAllLineWithAnyVehicleStopFitPattern(database.readableDatabase,patternName)
   }

   fun getAllLine():ArrayList<LineData>{
    return lineAccess.getAllLine(database.readableDatabase)
   }


   fun getTramDepartures(idStopPoint:String, combine:(Response<Departures>)->Unit){
     departVehicleAccess.getTramVehicleDepart(idStopPoint,combine)
   }

   fun getBusDepartures(idStopPoint:String,
    combine:(Response<Departures>)->Unit){ departVehicleAccess.getBusVehicleDepart(idStopPoint,combine)
   }


    fun getInfoAboutLineConcretDirectionLastStopID(numberLine: Long, lastStopId: Long): LineData {
      return   lineAccess.getInfoAboutLineConcretDirectionId(database.readableDatabase,numberLine,lastStopId)
    }

    fun getInfoAboutLineConcretDirectionLastStopName(numberLine: Int, lastStopName: String): LineData {
        return   lineAccess.getInfoAboutLineConcretDirectionName(database.readableDatabase,numberLine,lastStopName)
    }

    fun getTramPath(idTram: String, combine: (Response<JsonObject>) -> Unit) {
        positionVehicleAccess.getTramPath(idTram,combine)
    }

    fun getBusPath(idBus: String, combine: (Response<JsonObject>) -> Unit) {
        positionVehicleAccess.getBusPath(idBus,combine)
    }


    fun getTimeTableBus(tripId:String, vehicleId:String, combine:(Response<TimeTableData>)->Unit){
        timeTableVehicleAcess.getBusVehicleTimeTable(vehicleId,tripId,combine)
    }

    fun getTimeTableTram(tripId:String, vehicleId:String,combine:(Response<TimeTableData>)->Unit){
        timeTableVehicleAcess.getTramVehicleTimeTable(vehicleId,tripId,combine)
    }


    fun getTramPosition(lastUpdate: Long, combine: (Response<AllVehicles>) -> Unit) {
        positionVehicleAccess.getTramPosition(lastUpdate, combine)
    }

    fun getBusPosition(lastUpdate: Long, combine: (Response<AllVehicles>) -> Unit) {
        positionVehicleAccess.getBusPosition(lastUpdate, combine)
    }

    fun getAllVehiclesStop(): ArrayList<VehicleStopData> {
        return vehicleStopAccess.getAllVehicleStop(database.readableDatabase)
    }


    fun getVehicleStopLines(numberLine: Int):ArrayList<SequenceVehicleStopData>{
        return lineAccess.getVehicleStopsLine(database.readableDatabase,numberLine)
    }

    fun getVehicleStopById(shortId: Long): VehicleStopData {
        return vehicleStopAccess.getVehicleStopsByID(database.readableDatabase, shortId)
    }


    fun getNameVehicleStopByID(idVehicleStop: Int):String{
        return vehicleStopAccess.findNameBusStopById(database.readableDatabase,idVehicleStop)
    }

    fun  getVehicleStopIdByName( nameVehicleStop: String):String{
        return vehicleStopAccess.getVehicleStopIdByName(database.readableDatabase,nameVehicleStop)
    }

    fun isLineFavourite(numberLine:Int):Boolean{
        return favouriteLineAccess.isLineFavourite(database.readableDatabase,numberLine)
    }

    fun removeLinesFromFavourites(numberLine:Int){
        favouriteLineAccess.removeLineFromFavourite(database.writableDatabase,numberLine)
    }

    fun getAllFavouriteLine(): ArrayList<FavouriteLineData> {
        return favouriteLineAccess.getAllFavouriteLine(database.readableDatabase)
    }


    fun addLineToFavourite(nameLine:Int){
        favouriteLineAccess.addLineToFavorite(database.writableDatabase,nameLine)
    }


}