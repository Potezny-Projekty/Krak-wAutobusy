package com.example.krakowautobusy.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.krakowautobusy.ui.map.vehicledata.Vehicle

class MapViewModel : ViewModel() {

    private val mutableSelectedVehicle = MutableLiveData<Vehicle>()
    val selectedVehicle: LiveData<Vehicle> get() = mutableSelectedVehicle

    init{
        selectItem(Vehicle())
    }

    fun selectItem(vehicle: Vehicle) {
        mutableSelectedVehicle.value = vehicle
    }

}