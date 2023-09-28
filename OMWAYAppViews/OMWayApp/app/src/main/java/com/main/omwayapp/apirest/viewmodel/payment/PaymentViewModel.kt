package com.main.omwayapp.apirest.viewmodel.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.omwayapp.apirest.model.payment.PaymentItem
import com.main.omwayapp.apirest.repository.payment.RepositoryPayment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentViewModel: ViewModel() {
    private val repositoryPayment : RepositoryPayment = RepositoryPayment()

    private val _paymentState = MutableStateFlow<PaymentUIState>(PaymentUIState())
    val paymentState: StateFlow<PaymentUIState> = _paymentState

    fun findPaymentById(item:Int){
        viewModelScope.launch {
            _paymentState.update {it.copy(_loading = true)}
            val response = repositoryPayment.findPaymentById(item)
            _paymentState.update {it.copy(paymentItem = response)}
            _paymentState.update {it.copy(_loading = false)}
        }
    }

    data class PaymentUIState(
        val _loading:Boolean = false,
        val paymentItem: PaymentItem? = null
    )
}