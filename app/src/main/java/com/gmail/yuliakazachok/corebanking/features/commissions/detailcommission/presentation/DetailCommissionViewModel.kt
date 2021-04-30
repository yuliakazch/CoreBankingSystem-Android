package com.gmail.yuliakazachok.corebanking.features.commissions.detailcommission.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.DeleteCommissionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailCommissionViewModel @Inject constructor(
    private val deleteCommissionUseCase: DeleteCommissionUseCase
) : ViewModel(), EventsDispatcherOwner<DetailCommissionViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    val commission = MutableStateFlow<Commission?>(null)

    fun deleteCommission() = viewModelScope.launch {
        commission.value?.let {
            try {
                deleteCommissionUseCase(it.name)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}