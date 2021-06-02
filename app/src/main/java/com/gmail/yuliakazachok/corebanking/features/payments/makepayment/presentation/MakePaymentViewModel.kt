package com.gmail.yuliakazachok.corebanking.features.payments.makepayment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.GetCommissionsUseCase
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentCreate
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases.MakePaymentUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Date
import javax.inject.Inject

class MakePaymentViewModel @Inject constructor(
    private val makePaymentUseCase: MakePaymentUseCase,
    private val getCommissionsUseCase: GetCommissionsUseCase
) : ViewModel(), EventsDispatcherOwner<MakePaymentViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listCommission = MutableStateFlow<List<Commission>?>(null)
    val listCommission: Flow<List<Commission>>
        get() = _listCommission.filterNotNull()

    val idCredit = MutableStateFlow<Int?>(null)

    fun getCommissions() = viewModelScope.launch {
        try {
            _listCommission.value = getCommissionsUseCase()
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }

    fun makePayment(date: Date, sum: Float, idCommission: Int) = runBlocking {
        idCredit.value?.let {
            try {
                makePaymentUseCase(PaymentCreate(it, date, sum, idCommission))
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}