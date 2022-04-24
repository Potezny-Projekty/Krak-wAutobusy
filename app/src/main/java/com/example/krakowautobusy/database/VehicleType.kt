package com.example.krakowautobusy.database

enum class VehicleType(val nameVehicle: String, val number:Int, val nameInDatabase:String,val baseUrlTtss:String) {
    BUS("Autobus",1,"BUS","http://ttss.mpk.krakow.pl/internetservice/"),TRAM("Tramwaj",2,"TRAM","http://www.ttss.krakow.pl/internetservice/");
}