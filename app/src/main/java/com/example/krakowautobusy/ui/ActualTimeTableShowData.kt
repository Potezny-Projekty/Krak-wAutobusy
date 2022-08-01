package com.example.krakowautobusy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActualTimeTableShowData: ViewModel() {
    public var actualShowVehicleId:MutableLiveData<String> = MutableLiveData<String>("");

}