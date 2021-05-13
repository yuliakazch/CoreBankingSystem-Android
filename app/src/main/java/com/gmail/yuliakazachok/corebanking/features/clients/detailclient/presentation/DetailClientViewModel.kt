package com.gmail.yuliakazachok.corebanking.features.clients.detailclient.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientStates
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases.DeleteClientUseCase
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases.GetClientByPassportUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DetailClientViewModel @Inject constructor(
    private val getClientByPassportUseCase: GetClientByPassportUseCase,
    private val deleteClientUseCase: DeleteClientUseCase
) : ViewModel(), EventsDispatcherOwner<DetailClientViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _client = MutableStateFlow<Client?>(null)
    val client: Flow<Client>
        get() = _client.filterNotNull()

    fun getNumberPassport() = _client.value?.numberPassport ?: 0

    fun getClient(numberPassport: Long?) = viewModelScope.launch {
        numberPassport?.let {
            try {
                _client.value = getClientByPassportUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun deleteTariff() = runBlocking {
        _client.value?.let {
            try {
                deleteClientUseCase(it.numberPassport)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}