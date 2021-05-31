package com.gmail.yuliakazachok.corebanking.features.payments.paymentschedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.features.payments.listpayments.presentation.ListPaymentsViewModel
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases.GetPaymentScheduleUseCase
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases.GetPaymentsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentScheduleViewModel @Inject constructor(
    private val getPaymentScheduleUseCase: GetPaymentScheduleUseCase
) : ViewModel(), EventsDispatcherOwner<PaymentScheduleViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listPayments = MutableStateFlow<List<PaymentSchedule>?>(null)
    val listPayments: Flow<List<PaymentSchedule>>
        get() = _listPayments.filterNotNull()

    fun getPayments(idCredit: Int?) = viewModelScope.launch {
        idCredit?.let {
            try {
                _listPayments.value = getPaymentScheduleUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}