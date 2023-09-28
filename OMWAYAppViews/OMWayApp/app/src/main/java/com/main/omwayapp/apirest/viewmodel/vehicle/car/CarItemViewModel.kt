package com.main.omwayapp.apirest.viewmodel.vehicle.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.vehicle.CarDto
import com.main.omwayapp.apirest.repository.vehicle.RepositoryCar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CarItemViewModel: ViewModel() {
    val repositoryCar : RepositoryCar = RepositoryCar()

    var _carState = MutableStateFlow(false)
    val carState : StateFlow<Boolean> = _carState

    init {
        viewModelScope.launch {
            _carState.update {false}
        }
    }

    fun saveCar(item: CarDto){
        viewModelScope.launch {
            repositoryCar.save(item)
            _carState.update {true}
        }
    }

    fun updateCar(item: CarDto){
        viewModelScope.launch {
            repositoryCar.update(item)
            _carState.update {true}
        }
    }

    fun deleteCar(item: String){
        viewModelScope.launch {
            repositoryCar.delete(item)
            _carState.update {true}
        }
    }

}