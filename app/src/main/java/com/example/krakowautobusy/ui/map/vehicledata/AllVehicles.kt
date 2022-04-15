package com.example.krakowautobusy.ui.map.vehicledata

import Vehicle
import kotlinx.serialization.Serializable

@Serializable
data class AllVehicles(
    val lastUpdate : Long,
    val vehicles : ArrayList<Vehicle>
)

