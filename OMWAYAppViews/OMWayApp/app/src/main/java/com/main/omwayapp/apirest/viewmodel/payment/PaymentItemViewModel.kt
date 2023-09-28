package com.main.omwayapp.apirest.viewmodel.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.dto.payment.PaymentDto
import com.main.omwayapp.apirest.repository.payment.RepositoryPayment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentItemViewModel:ViewModel() {
    val repositoryPayment : RepositoryPayment = RepositoryPayment()

    var _paymentState = MutableStateFlow(false)
    val paymentState : StateFlow<Boolean> = _paymentState

    init {
        viewModelScope.launch {
            _paymentState.update {false}
        }
    }

    fun saveCash(item: PaymentDto){
        viewModelScope.launch {
            repositoryPayment.save(item)
            _paymentState.update {true}
        }
    }

    fun updateCash(item: PaymentDto){
        viewModelScope.launch {
            repositoryPayment.update(item)
            _paymentState.update {true}
        }
    }
}