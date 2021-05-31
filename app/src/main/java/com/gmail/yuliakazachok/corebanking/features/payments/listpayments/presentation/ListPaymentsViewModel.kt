package com.gmail.yuliakazachok.corebanking.features.payments.listpayments.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases.GetPaymentsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListPaymentsViewModel @Inject constructor(
    private val getPaymentsUseCase: GetPaymentsUseCase
) : ViewModel(), EventsDispatcherOwner<ListPaymentsViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listPayments = MutableStateFlow<List<Payment>?>(null)
    val listPayments: Flow<List<Payment>>
        get() = _listPayments.filterNotNull()

    fun getPayments(idCredit: Int?) = viewModelScope.launch {
        idCredit?.let {
            try {
                _listPayments.value = getPaymentsUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}