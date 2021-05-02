package com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.GetTariffsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainTariffsViewModel @Inject constructor(
    private val getTariffsUseCase: GetTariffsUseCase
) : ViewModel(), EventsDispatcherOwner<MainTariffsViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
        fun goToDetailTariff(idTariff: Int)
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listTariff = MutableStateFlow<List<Tariff>?>(null)
    val listTariff: Flow<List<Tariff>>
        get() = _listTariff.filterNotNull()

    fun getTariffs() = viewModelScope.launch {
        try {
            _listTariff.value = getTariffsUseCase()
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }

    fun goToDetail(idTariff: Int) {
        eventsDispatcher.dispatchEvent { goToDetailTariff(idTariff) }
    }
}