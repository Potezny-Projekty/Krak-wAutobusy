package com.krak.krakowautobusy.ui.detailsline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActualTimeTableShowData: ViewModel() {
    public var actualShowVehicleId:MutableLiveData<String> = MutableLiveData<String>("");

}