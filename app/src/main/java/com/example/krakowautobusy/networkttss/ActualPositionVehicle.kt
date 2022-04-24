package com.example.krakowautobusy.networkttss

import com.example.krakowautobusy.ui.map.network.ActualPositionApi
import com.example.krakowautobusy.ui.map.network.RetrofitHelperBus
import com.example.krakowautobusy.ui.map.network.RetrofitHelperTram

class ActualPositionVehicle:ActualPositionVehicleInterface {

    private val tramHelperInstance = RetrofitHelperTram.getInstance()
        .create(ActualPositionRetrofitApi::class.java)

    private val busHelperInstance = RetrofitHelperBus
        .getInstance().create(ActualPositionRetrofitApi::class.java)




    override fun getAllVehicleesPosition() {
        TODO("Not yet implemented")
    }

    override fun getBusPosition() {
        TODO("Not yet implemented")
    }

    override fun getTramPosition() {
        TODO("Not yet implemented")
    }
}