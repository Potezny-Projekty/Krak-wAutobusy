package com.example.krakowautobusy.ui.map.network.requestData

data class ActualPositionData(
    var lastUpdate : Long = 0,
    val positionType : String = "CORRECTED",
    val colorType : String = "ROUTE_BASED",
    val language : String = "pl"
)
