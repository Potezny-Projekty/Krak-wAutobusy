package com.example.krakowautobusy.ui.map.vehicledata

import kotlinx.serialization.Serializable

@Serializable
data class TimeTableData (val actual:ArrayList<StopData>,val old:ArrayList<StopData>)

@Serializable
data class StopData (val name:String)

@Serializable
data class StatusData (val actualTime:String,val status:String,val stop_seq_num:String,val stop:StopData)