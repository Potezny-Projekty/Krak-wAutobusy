package com.example.krakowautobusy.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.krakowautobusy.ui.map.vehicledata.Vehicle

class MapViewModel : ViewModel() {

    private val mutableIsFavourit = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isFavourit: LiveData<Boolean> get() = mutableIsFavourit.apply {
        value = false
    }

    private val mutableSetMyLocation = MutableLiveData<Boolean>()
    val setMyLocation: LiveData<Boolean> get() = mutableSetMyLocation

    fun isFavouritMap() {
        mutableIsFavourit.value = mutableIsFavourit.value == false
    }

    fun isSetLocation() {
        mutableSetMyLocation.value = mutableSetMyLocation.value == false
    }
}