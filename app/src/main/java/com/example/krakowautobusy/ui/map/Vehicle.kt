package com.example.krakowautobusy.ui.map
import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val heading : Int = 1,
    val latitude : Long = 1L,
    val longitude : Long = 1L,
    val category : String = "dfs",
    val id : String = " ",
    val tripId : String = "",
    val name : String = "",
val isDeleted:Boolean=false
)
