package com.main.omwayapp.apirest.viewmodel.omwayuser.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.repository.omwayuser.RepositoryDriver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//Es para guardar y actualizar
//Utiliza clases Dto

class DriverItemViewModel:ViewModel() {
    private val repositoryDriver : RepositoryDriver = RepositoryDriver()

    var _driverState = MutableStateFlow(false)
    val driverState : StateFlow<Boolean> = _driverState

    init {
        viewModelScope.launch {
            _driverState.update {false}
        }
    }

    fun saveDriver(item: DriverDto){
        viewModelScope.launch {
            repositoryDriver.save(item)
            _driverState.update {true}
        }
    }

    fun updateDriver(item: DriverDto){
        viewModelScope.launch {
            repositoryDriver.update(item)
            _driverState.update {true}
        }
    }

    fun deleteDriver(item: String){
        viewModelScope.launch {
            repositoryDriver.delete(item)
            _driverState.update {true}
        }
    }
}