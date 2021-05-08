package com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.AvailableTariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.GetTariffsByPassportUseCase
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.GetTariffsNotByPassportUseCase
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.GetTariffsUseCase
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.SaveAvailableTariffUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainTariffsViewModel @Inject constructor(
    private val getTariffsUseCase: GetTariffsUseCase,
    private val getTariffsByPassportUseCase: GetTariffsByPassportUseCase,
    private val getTariffsNotByPassportUseCase: GetTariffsNotByPassportUseCase,
    private val saveAvailableTariffUseCase: SaveAvailableTariffUseCase
) : ViewModel(), EventsDispatcherOwner<MainTariffsViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
        fun goToDetailTariff(idTariff: Int)
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listTariff = MutableStateFlow<List<Tariff>?>(null)
    val listTariff: Flow<List<Tariff>>
        get() = _listTariff.filterNotNull()

    private val _listTariffsNotClient = MutableStateFlow<List<Tariff>?>(null)
    val listTariffsNotClient: Flow<List<Tariff>>
        get() = _listTariffsNotClient.filterNotNull()

    val mode = MutableStateFlow<String?>(null)
    val numberPassport = MutableStateFlow<Long?>(null)

    fun getTariffs() = viewModelScope.launch {
        try {
            _listTariff.value = getTariffsUseCase()
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }

    fun getClientTariffs() = runBlocking {
        numberPassport.value?.let {
            try {
                _listTariff.value = getTariffsByPassportUseCase(it)
                getTariffsNotClient(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun saveAvailableTariff(idTariff: Int) = runBlocking {
        numberPassport.value?.let { number ->
            try {
                saveAvailableTariffUseCase(
                    AvailableTariff(
                        idTariff = idTariff,
                        numberPassport = number
                    )
                )
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    private fun getTariffsNotClient(numberPassport: Long) = viewModelScope.launch {
        try {
            _listTariffsNotClient.value = getTariffsNotByPassportUseCase(numberPassport)
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }

    fun isEmptyListTariffs() = _listTariff.value?.isEmpty() ?: true

    fun goToDetail(idTariff: Int) {
        eventsDispatcher.dispatchEvent { goToDetailTariff(idTariff) }
    }
}