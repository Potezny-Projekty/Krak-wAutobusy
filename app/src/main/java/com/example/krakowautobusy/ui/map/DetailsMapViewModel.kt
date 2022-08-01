package com.example.krakowautobusy.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsMapViewModel : ViewModel() {

    private val mutableSetMyLocation = MutableLiveData<Boolean>()
    val setMyLocation: LiveData<Boolean> get() = mutableSetMyLocation

    init{
        mutableSetMyLocation.value = false
    }

    fun isSetLocation() {
        mutableSetMyLocation.value = mutableSetMyLocation.value == false
    }
}