package com.main.omwayapp.apirest.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.settings.ConfigFareItem
import com.main.omwayapp.apirest.repository.settings.RepositoryConfigFare
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfigFareViewModel:ViewModel() {
    private val repositoryConfigFare : RepositoryConfigFare = RepositoryConfigFare()

    private val _configFareState = MutableStateFlow<ConfigFareListUIState>(ConfigFareListUIState())
    val configFareState: StateFlow<ConfigFareListUIState> = _configFareState


    fun getAllConfigFare(){
        viewModelScope.launch {
            _configFareState.update {it.copy(_loading = true)}
            val response = repositoryConfigFare.getAll()
            _configFareState.update {it.copy(listConfigFare = response)}
            _configFareState.update {it.copy(_loading = false)}
        }
    }

    data class ConfigFareListUIState(
        val _loading:Boolean = false,
        val listConfigFare:List<ConfigFareItem> = emptyList()
    )
}