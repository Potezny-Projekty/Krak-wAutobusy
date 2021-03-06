package com.example.krakowautobusy.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.krakowautobusy.ui.map.vehicledata.Vehicle

class MapViewModel : ViewModel() {

    private val mutableIsFavourit = MutableLiveData<Boolean>()
    val isFavourit: LiveData<Boolean> get() = mutableIsFavourit

    private val mutableSetMyLocation = MutableLiveData<Boolean>()
    val setMyLocation: LiveData<Boolean> get() = mutableSetMyLocation

    init{
        mutableIsFavourit.value = false
        mutableSetMyLocation.value = false
    }

    fun isFavouritMap() {
        mutableIsFavourit.value = mutableIsFavourit.value == false
    }

    fun isSetLocation() {
        mutableSetMyLocation.value = mutableSetMyLocation.value == false
    }
}