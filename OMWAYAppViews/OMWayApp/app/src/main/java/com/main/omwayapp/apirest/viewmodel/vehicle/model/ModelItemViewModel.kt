package com.main.omwayapp.apirest.viewmodel.vehicle.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.vehicle.ModelDto
import com.main.omwayapp.apirest.repository.vehicle.RepositoryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ModelItemViewModel:ViewModel() {
    private val repositoryModel : RepositoryModel = RepositoryModel()

    var _modelState = MutableStateFlow(false)
    val modelState : StateFlow<Boolean> = _modelState

    init {
        viewModelScope.launch {
            _modelState.update {false}
        }
    }

    fun saveModel(item: ModelDto){
        viewModelScope.launch {
            repositoryModel.save(item)
            _modelState.update {true}
        }
    }

    fun updateModel(item: ModelDto){
        viewModelScope.launch {
            repositoryModel.update(item)
            _modelState.update {true}
        }
    }

    fun deleteModel(item: Int){
        viewModelScope.launch {
            repositoryModel.delete(item)
            _modelState.update {true}
        }
    }
}