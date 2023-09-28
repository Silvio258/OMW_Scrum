package com.main.omwayapp.apirest.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.settings.ConfigFareItem
import com.main.omwayapp.apirest.repository.settings.RepositoryConfigFare
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfigFareItemViewModel:ViewModel() {
    private val repositoryConfigFare : RepositoryConfigFare = RepositoryConfigFare()

    var _configFareState = MutableStateFlow(false)
    val configFareState : StateFlow<Boolean> = _configFareState

    init {
        viewModelScope.launch {
            _configFareState.update {false}
        }
    }

    fun saveConfigFare(item: ConfigFareItem){
        viewModelScope.launch {
            repositoryConfigFare.save(item)
            _configFareState.update {true}
        }
    }

    fun updateConfigFare(item: ConfigFareItem){
        viewModelScope.launch {
            repositoryConfigFare.update(item)
            _configFareState.update {true}
        }
    }

    fun deleteConfigFare(item: Int){
        viewModelScope.launch {
            repositoryConfigFare.delete(item)
            _configFareState.update {true}
        }
    }
}