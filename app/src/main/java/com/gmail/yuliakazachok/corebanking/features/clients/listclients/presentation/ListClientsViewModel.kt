package com.gmail.yuliakazachok.corebanking.features.clients.listclients.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientStates
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases.SearchClientsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListClientsViewModel @Inject constructor(
    private val searchClientsUseCase: SearchClientsUseCase
) : ViewModel(), EventsDispatcherOwner<ListClientsViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
        fun goToDetailClient(numberPassport: Long)
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listClients = MutableStateFlow<List<Client>?>(null)
    val listClients: Flow<List<Client>>
        get() = _listClients.filterNotNull()

    fun getClients(filters: ClientFilters?) = viewModelScope.launch {
        filters?.let {
            try {
                _listClients.value = searchClientsUseCase(filters)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun getStateClient(isCredit: Boolean, isTariff: Boolean, countBlockDays: Int) = when {
        countBlockDays != 0 -> ClientStates.STATE_BLOCKED
        isTariff && !isCredit -> ClientStates.STATE_NOT_CREDIT
        isCredit -> ClientStates.STATE_YES_CREDIT
        else -> ClientStates.STATE_NOT_TARIFF
    }

    fun goToDetail(numberPassport: Long) {
        eventsDispatcher.dispatchEvent { goToDetailClient(numberPassport) }
    }
}