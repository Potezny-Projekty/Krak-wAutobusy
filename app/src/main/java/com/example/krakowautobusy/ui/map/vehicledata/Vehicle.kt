package com.example.krakowautobusy.ui.map.vehicledata

import com.example.krakowautobusy.ui.map.vehicledata.PathVehicle
import kotlinx.serialization.Serializable


@Serializable
data class Vehicle(
    val isDeleted : Boolean = false,
    val heading : Int = 0,
    val latitude : Long = 0L,
    val longitude : Long = 0L,
    val category : String = "",
    val id : String = "",
    val tripId : String = "",
    val name : String = "",
    val path : ArrayList<PathVehicle> = ArrayList()

)
