package com.krak.krakowautobusy.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsMapViewModel : ViewModel() {

    private val mutableSetMyLocation = MutableLiveData<Boolean>().apply {
        value = false
    }
    val setMyLocation: LiveData<Boolean> get() = mutableSetMyLocation


    fun isSetLocation() {
        mutableSetMyLocation.value = mutableSetMyLocation.value == false
    }
}