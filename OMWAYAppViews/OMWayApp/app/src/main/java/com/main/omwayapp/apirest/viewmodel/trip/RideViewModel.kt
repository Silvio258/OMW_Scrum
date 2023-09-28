package com.main.omwayapp.apirest.viewmodel.trip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.trip.RideItem
import com.main.omwayapp.apirest.repository.trip.RepositoryRide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class RideViewModel:ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val repositoryRide : RepositoryRide = RepositoryRide()

    val _discontinuedRideState = MutableStateFlow<DiscontinuedRideListUIState>(DiscontinuedRideListUIState())
    val discontinuedRideState: StateFlow<DiscontinuedRideListUIState> = _discontinuedRideState

    val _inProgressRideState = MutableStateFlow<InProgressRideListUIState>(InProgressRideListUIState())
    val inProgressRideState: StateFlow<InProgressRideListUIState> = _inProgressRideState

    val _requestedRideState = MutableStateFlow<RequestedListUIState>(RequestedListUIState())
    val requestedsRideState: StateFlow<RequestedListUIState> = _requestedRideState


    fun findInProgressRidesByRiderCif(cif:String){
        viewModelScope.launch {
            _inProgressRideState.update {it.copy(_loading = true)}
            val response = repositoryRide.findInProgressRidesByRiderCif(cif)
            _inProgressRideState.update {it.copy(listInProgressRides = response)}
            _inProgressRideState.update {it.copy(_loading = false)}
        }
    }
    fun findDiscontinuedRideByRiderCif(cif:String){
        viewModelScope.launch {
            _discontinuedRideState.update {it.copy(_loading = true)}
            val response = repositoryRide.findDiscontinuedRideByRiderCif(cif)
            _discontinuedRideState.update {it.copy(listDiscontinuedRides = response)}
            _discontinuedRideState.update {it.copy(_loading = false)}
        }
    }
    fun findInProgressRidesByDriverCif(cif:String){
        viewModelScope.launch {
            _inProgressRideState.update {it.copy(_loading = true)}
            val response = repositoryRide.findInProgressRidesByDriverCif(cif)
            _inProgressRideState.update {it.copy(listInProgressRides = response)}
            _inProgressRideState.update {it.copy(_loading = false)}
        }
    }
    fun findDiscontinuedRidesByDriverCif(cif:String){
        viewModelScope.launch {
            _discontinuedRideState.update {it.copy(_loading = true)}
            val response = repositoryRide.findDiscontinuedRidesByDriverCif(cif)
            _discontinuedRideState.update {it.copy(listDiscontinuedRides = response)}
            _discontinuedRideState.update {it.copy(_loading = false)}
        }
    }
    fun getRequestedRides(){
        viewModelScope.launch {
            _requestedRideState.update {it.copy(_loading = true)}
            val response = repositoryRide.getRequestedRides()
            _requestedRideState.update {it.copy(listRequestedRides = response)}
            _requestedRideState.update {it.copy(_loading = false)}
        }
    }


    data class DiscontinuedRideListUIState(
        val _loading:Boolean = false,
        val listDiscontinuedRides:List<RideItem> = emptyList()
    )
    data class InProgressRideListUIState(
        val _loading:Boolean = false,
        val listInProgressRides:List<RideItem> = emptyList()
    )
    data class RequestedListUIState(
        val _loading:Boolean = false,
        val listRequestedRides:List<RideItem> = emptyList()
    )
}