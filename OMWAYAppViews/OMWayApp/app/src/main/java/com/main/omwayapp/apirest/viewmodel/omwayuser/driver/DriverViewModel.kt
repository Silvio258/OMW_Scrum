package com.main.omwayapp.apirest.viewmodel.omwayuser.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.omwayuser.DriverItem
import com.main.omwayapp.apirest.repository.omwayuser.RepositoryDriver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//Es para traer datos
//Utiliza objetos completos
class DriverViewModel:ViewModel() {

    val repositoryDriver : RepositoryDriver = RepositoryDriver()

    val _driverState = MutableStateFlow<DriverUIState>(DriverUIState())
    val driverState:StateFlow<DriverUIState> = _driverState

    val _driverListState = MutableStateFlow<DriverListUIState>(DriverListUIState())
    val driverListState:StateFlow<DriverListUIState> = _driverListState

    fun findDriverByCif(item:String){
        viewModelScope.launch {
            _driverState.update {it.copy(_loading = true)}
            val response = repositoryDriver.findByCif(item)
            _driverState.update {it.copy(driverItem = response)}
            _driverState.update {it.copy(_loading = false)}
        }
    }

    fun getAllDriver(){
        viewModelScope.launch {
            _driverListState.update {it.copy(_loading = true)}
            val response = repositoryDriver.getAll()
            _driverListState.update {it.copy(listDriver = response)}
            _driverListState.update {it.copy(_loading = false)}
        }
    }

    data class DriverListUIState(
        val _loading:Boolean = false,
        val listDriver:List<DriverItem> = emptyList()
    )

    data class DriverUIState(
        val _loading:Boolean = false,
        val driverItem: DriverItem = DriverItem()
    )
}