package com.krak.krakowautobusy.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActualTimeTableShowData: ViewModel() {
    public var actualShowVehicleId:MutableLiveData<String> = MutableLiveData<String>("");

}