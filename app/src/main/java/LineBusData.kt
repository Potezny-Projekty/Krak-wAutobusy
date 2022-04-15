
data class LineBusData(
    val id: Int, val isBus: Vehicle, val numberLine: Int, val startBusStop: String,
    val endBusStop: String, val isFavourite: Boolean
)

enum class Vehicle { BUS, TRAM }