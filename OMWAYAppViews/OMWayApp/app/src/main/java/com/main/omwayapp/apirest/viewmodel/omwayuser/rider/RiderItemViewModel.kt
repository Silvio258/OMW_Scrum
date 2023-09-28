package com.main.omwayapp.apirest.viewmodel.omwayuser.rider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.omwayuser.RiderDto
import com.main.omwayapp.apirest.repository.omwayuser.RepositoryRider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RiderItemViewModel:ViewModel() {
    val repositoryRider : RepositoryRider = RepositoryRider()

    var _riderState = MutableStateFlow(false)
    val riderState : StateFlow<Boolean> = _riderState

    init {
        viewModelScope.launch {
            _riderState.update {false}
        }
    }

    fun saveRider(item: RiderDto){
        viewModelScope.launch {
            repositoryRider.save(item)
            _riderState.update {true}
        }
    }

    fun updateRider(item: RiderDto){
        viewModelScope.launch {
            repositoryRider.update(item)
            _riderState.update {true}
        }
    }

    fun deleteRider(item: String){
        viewModelScope.launch {
            repositoryRider.delete(item)
            _riderState.update {true}
        }
    }
}