package com.gmail.yuliakazachok.corebanking.features.clients.detailclient.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases.GetClientByPassportUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailClientViewModel @Inject constructor(
    private val getClientByPassportUseCase: GetClientByPassportUseCase
) : ViewModel(), EventsDispatcherOwner<DetailClientViewModel.EventListener> {

    companion object {
        const val STATE_NOT_TARIFF = 0
        const val STATE_NOT_CREDIT = 1
        const val STATE_YES_CREDIT = 2
        const val STATE_BLOCKED = 3
    }

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _client = MutableStateFlow<Client?>(null)
    val client: Flow<Client>
        get() = _client.filterNotNull()

    fun getStateClient(isCredit: Boolean, isTariff: Boolean, countBlockDays: Int) = when {
        countBlockDays != 0 -> STATE_BLOCKED
        isTariff && !isCredit -> STATE_NOT_CREDIT
        isCredit -> STATE_YES_CREDIT
        else -> STATE_NOT_TARIFF
    }

    fun getClient(numberPassport: Long?) = viewModelScope.launch {
        numberPassport?.let {
            try {
                _client.value = getClientByPassportUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}