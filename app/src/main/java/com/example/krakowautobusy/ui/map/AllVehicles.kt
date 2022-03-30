package com.example.krakowautobusy.ui.map

import kotlinx.serialization.Serializable


@Serializable
data class AllVehicles(
    val vehicles: List<Vehicle>)
