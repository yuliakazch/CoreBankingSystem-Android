package com.gmail.yuliakazachok.corebanking.features.tariffs.edittariff.presentation

import androidx.lifecycle.ViewModel
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases.SaveTariffUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class EditTariffViewModel @Inject constructor(
    private val saveTariffUseCase: SaveTariffUseCase
) : ViewModel(), EventsDispatcherOwner<EditTariffViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val flowId = MutableStateFlow<Int>(0)
    val flowName = MutableStateFlow<String?>(null)
    val flowRate = MutableStateFlow<String?>(null)
    val flowMinSum = MutableStateFlow<String?>(null)
    val flowMaxSum = MutableStateFlow<String?>(null)
    val flowMinTerm = MutableStateFlow<String?>(null)
    val flowMaxTerm = MutableStateFlow<String?>(null)

    fun setData(tariff: Tariff?) {
        tariff?.let {
            flowId.value = it.id
            flowName.value = it.name
            flowRate.value = it.rate.toString()
            flowMinSum.value = it.minSum.toString()
            flowMaxSum.value = it.maxSum.toString()
            flowMinTerm.value = it.minTerm.toString()
            flowMaxTerm.value = it.maxTerm.toString()
        }
    }

    fun saveTariff() = runBlocking {
        val name = flowName.value
        val rate = flowRate.value
        if (name != null && rate != null) {
            try {
                saveTariffUseCase(
                    Tariff(
                        flowId.value,
                        name,
                        rate.toInt(),
                        flowMinSum.value?.toInt() ?: 0,
                        flowMaxSum.value?.toInt() ?: 0,
                        flowMinTerm.value?.toInt() ?: 0,
                        flowMaxTerm.value?.toInt() ?: 0
                    )
                )
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}