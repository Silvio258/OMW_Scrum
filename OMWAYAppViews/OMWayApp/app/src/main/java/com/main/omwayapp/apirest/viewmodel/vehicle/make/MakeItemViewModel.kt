package com.main.omwayapp.apirest.viewmodel.vehicle.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.vehicle.MakeDto
import com.main.omwayapp.apirest.repository.vehicle.RepositoryMake
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MakeItemViewModel:ViewModel() {
    private val repositoryMake : RepositoryMake = RepositoryMake()

    var _makeState = MutableStateFlow(false)
    val makeState : StateFlow<Boolean> = _makeState

    init {
        viewModelScope.launch {
            _makeState.update {false}
        }
    }

    fun saveMake(item: MakeDto){
        viewModelScope.launch {
            repositoryMake.save(item)
            _makeState.update {true}
        }
    }

    fun updateMake(item: MakeDto){
        viewModelScope.launch {
            repositoryMake.update(item)
            _makeState.update {true}
        }
    }

    fun deleteMake(item: Int){
        viewModelScope.launch {
            repositoryMake.delete(item)
            _makeState.update {true}
        }
    }
}