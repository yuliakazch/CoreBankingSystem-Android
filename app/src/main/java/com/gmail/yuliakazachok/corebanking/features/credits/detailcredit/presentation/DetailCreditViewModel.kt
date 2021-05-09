package com.gmail.yuliakazachok.corebanking.features.credits.detailcredit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.usecases.GetActiveCreditUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailCreditViewModel @Inject constructor(
    private val getActiveCreditUseCase: GetActiveCreditUseCase
) : ViewModel(), EventsDispatcherOwner<DetailCreditViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _credit = MutableStateFlow<Credit?>(null)
    val credit: Flow<Credit>
        get() = _credit.filterNotNull()

    fun getNumberPassport() = _credit.value?.numberPassport ?: 0

    fun getCredit(numberPassport: Long?) = viewModelScope.launch {
        numberPassport?.let {
            try {
                _credit.value = getActiveCreditUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}