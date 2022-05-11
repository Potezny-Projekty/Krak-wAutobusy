package com.example.krakowautobusy.database

enum class FavouriteLineTable(val nameColumn: String,val indexColumn:Int) {
    ID_FAVOURITE_LINE("idFavouriteLine",0),
    ID_LINE("idLine",1)
}

enum class LineTable(val nameColumn: String,val indexColumn:Int) {
    ID_LINE("Line.idLine",0),
    NUMBER_LINE("Line.numberLine",1),
    FIRST_STOP_ID("Line.firstStop",2),
    LAST_STOP_ID("Line.lastStop",3),
    ID_VEHICLE("Line.idVehicle",4)
}

enum class VehicleStopTable(val nameColumn: String,val indexColumn:Int) {
    ID_VEHICLE_STOP("VehicleStop.idVehicleStop",0),
    NAME("VehicleStop.name",1),
    LONGITUDE("VehicleStop.longitude",2),
    LATITUDE("VehicleStop.latitude",3),
    ID_SHORT("VehicleStop.idShort",4),
    VEHICLE_TYPE("VehicleStop.vehicleType",5),
    ID_STOP_POINT("VehicleStop.idStopPoint",5),

}



enum class TableName(val nameTable:String){
    FAVOURITE_LINE("FavouriteLine"),LINE("Line"),VEHICLE_STOP("VehicleStop")
}

val TABLE_NO_ELEMENT=0


