package com.gmail.yuliakazachok.corebanking.features.commissions.editcommission.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.SaveCommissionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditCommissionViewModel @Inject constructor(
    private val saveCommissionUseCase: SaveCommissionUseCase
) : ViewModel(), EventsDispatcherOwner<EditCommissionViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    fun saveCommission(name: String, interest: Int) = viewModelScope.launch {
        try {
            saveCommissionUseCase(Commission(name, interest))
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }
}