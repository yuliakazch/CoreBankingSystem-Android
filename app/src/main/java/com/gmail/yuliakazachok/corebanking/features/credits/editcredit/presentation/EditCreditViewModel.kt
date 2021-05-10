package com.gmail.yuliakazachok.corebanking.features.credits.editcredit.presentation

import androidx.lifecycle.ViewModel
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditCreate
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.usecases.SaveCreditUseCase
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.GetTariffsByPassportUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.runBlocking
import java.sql.Date
import javax.inject.Inject

class EditCreditViewModel @Inject constructor(
    private val getTariffsByPassportUseCase: GetTariffsByPassportUseCase,
    private val saveCreditUseCase: SaveCreditUseCase
) : ViewModel(), EventsDispatcherOwner<EditCreditViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listTariff = MutableStateFlow<List<Tariff>?>(null)
    val listTariff: Flow<List<Tariff>>
        get() = _listTariff.filterNotNull()

    val numberPassport = MutableStateFlow<Long?>(null)

    fun getTariffs() = runBlocking {
        numberPassport.value?.let {
            try {
                _listTariff.value = getTariffsByPassportUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun saveCredit(idTariff: Int, term: Int, sum: Int, dateOpen: Date) = runBlocking {
        numberPassport.value?.let {
            try {
                saveCreditUseCase(CreditCreate(it, idTariff, term, sum, dateOpen))
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}