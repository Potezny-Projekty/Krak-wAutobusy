package com.example.krakowautobusy.ui.map.vehicledata

import kotlinx.serialization.Serializable

/**
 * Koła autobusu kręcą kręcą się a autobus mknie (<-xD tak się to piszę) a tramwaj też mknie
 * DUPA
 * Kurwa nie usuwać
 */
@Serializable
data class PathVehicle(
    val y1 : Long = 180243382,
    val length : Double = 0.00030179980498470455,
    val x1 : Long =	71732434,
    val y2 : Long =	180244290,
    val angle : Int =	22,
    val x2 : Long =	71733029,
)
