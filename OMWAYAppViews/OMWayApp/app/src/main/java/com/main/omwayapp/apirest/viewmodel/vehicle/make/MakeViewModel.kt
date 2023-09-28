package com.main.omwayapp.apirest.viewmodel.vehicle.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.vehicle.MakeItem
import com.main.omwayapp.apirest.repository.vehicle.RepositoryMake
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MakeViewModel:ViewModel() {
    private val repositoryMake : RepositoryMake = RepositoryMake()

    val _makeState = MutableStateFlow<MakeListUIState>(MakeListUIState())
    val makeState:StateFlow<MakeListUIState> = _makeState


    fun getAllMake(){
        viewModelScope.launch {
            _makeState.update {it.copy(_loading = true)}
            val response = repositoryMake.getAll()
            _makeState.update {it.copy(listMake = response)}
            _makeState.update {it.copy(_loading = false)}
        }
    }

    data class MakeListUIState(
        val _loading:Boolean = false,
        val listMake:List<MakeItem> = emptyList()
    )
}