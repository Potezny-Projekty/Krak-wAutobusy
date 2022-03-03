package com.example.krakowautobusy.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class StaticFillDatabase(override var instance: Database,
                         override var vehicleDatabase: Database_VehicleType
) :FillDatabase {
    private var db: SQLiteDatabase = instance.writableDatabase



    init{
        this.instance=instance
        this.db = instance.writableDatabase
        this.vehicleDatabase=StaticDatabaseVehicleType()
    }


    override fun fill_VehicleTypeTable() {
        super.fill_VehicleTypeTable()

        val listOfVehicle=ArrayList<Database_VehicleType.VehicleDatabase>()
        listOfVehicle.add(Database_VehicleType.VehicleDatabase("BUS"))
        listOfVehicle.add(Database_VehicleType.VehicleDatabase("TRAM"))


        vehicleDatabase.insertVehicleTypeDataRowToDatabase(instance.writableDatabase,listOfVehicle[0])
        vehicleDatabase.insertVehicleTypeDataRowToDatabase(instance.writableDatabase,listOfVehicle[1])







    }
}