package com.gmail.yuliakazachok.corebanking.features.tariffs.detailtariff.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.DeleteTariffUseCase
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.GetTariffByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DetailTariffViewModel @Inject constructor(
    private val getTariffByIdUseCase: GetTariffByIdUseCase,
    private val deleteTariffUseCase: DeleteTariffUseCase
) : ViewModel(), EventsDispatcherOwner<DetailTariffViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _tariff = MutableStateFlow<Tariff?>(null)
    val tariff: Flow<Tariff>
        get() = _tariff.filterNotNull()

    fun getTariff(): Tariff? = _tariff.value

    fun getTariffById(idTariff: Int?) = viewModelScope.launch {
        idTariff?.let {
            try {
                _tariff.value = getTariffByIdUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun deleteTariff() = runBlocking {
        _tariff.value?.let {
            try {
                deleteTariffUseCase(it.id)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}