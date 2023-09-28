package com.main.omwayapp.apirest.viewmodel.omwayuser.rider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.omwayuser.RiderItem
import com.main.omwayapp.apirest.repository.omwayuser.RepositoryRider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RiderViewModel:ViewModel() {
    val repositoryRider : RepositoryRider = RepositoryRider()

    val _riderState = MutableStateFlow(RiderUIState())
    val riderState: StateFlow<RiderUIState> = _riderState

    val _riderListState = MutableStateFlow(RiderListUIState())
    val riderListState: StateFlow<RiderListUIState> = _riderListState

    fun findRiderByCif(item:String){
        viewModelScope.launch {
            _riderState.update {it.copy(_loading = true)}
            val response = repositoryRider.findByCif(item)
            _riderState.update {it.copy(riderItem = response)}
            _riderState.update {it.copy(_loading = false)}
        }
    }

    fun getAllRider(){
        viewModelScope.launch {
            _riderListState.update {it.copy(_loading = true)}
            val response = repositoryRider.getAll()
            _riderListState.update {it.copy(listRider = response)}
            _riderListState.update {it.copy(_loading = false)}
        }
    }

    data class RiderListUIState(
        val _loading:Boolean = false,
        val listRider:List<RiderItem> = emptyList()
    )

    data class RiderUIState(
        val _loading:Boolean = false,
        val riderItem: RiderItem = RiderItem()
    )
}