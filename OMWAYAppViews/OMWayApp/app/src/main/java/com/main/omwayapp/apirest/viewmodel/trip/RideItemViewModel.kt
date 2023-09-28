package com.main.omwayapp.apirest.viewmodel.trip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.trip.RideDto
import com.main.omwayapp.apirest.repository.trip.RepositoryRide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class RideItemViewModel:ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    val repositoryRide : RepositoryRide = RepositoryRide()

    var _rideState = MutableStateFlow(false)
    val rideState : StateFlow<Boolean> = _rideState

    init {
        viewModelScope.launch {
            _rideState.update {false}
        }
    }

    fun saveRide(item: RideDto){
        viewModelScope.launch {
            repositoryRide.save(item)
            _rideState.update {true}
        }
    }

    fun updateRide(item: RideDto){
        viewModelScope.launch {
            repositoryRide.update(item)
            _rideState.update {true}
        }
    }
}