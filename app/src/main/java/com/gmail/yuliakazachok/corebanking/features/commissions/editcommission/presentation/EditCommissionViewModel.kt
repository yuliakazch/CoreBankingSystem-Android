package com.gmail.yuliakazachok.corebanking.features.commissions.editcommission.presentation

import androidx.lifecycle.ViewModel
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.SaveCommissionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class EditCommissionViewModel @Inject constructor(
    private val saveCommissionUseCase: SaveCommissionUseCase
) : ViewModel(), EventsDispatcherOwner<EditCommissionViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val flowId = MutableStateFlow<Int>(0)
    val flowName = MutableStateFlow<String?>(null)
    val flowInterest = MutableStateFlow<String?>(null)

    fun setData(commission: Commission?) {
        commission?.let {
            flowId.value = it.id
            flowName.value = it.name
            flowInterest.value = it.interest.toString()
        }
    }

    fun saveCommission() = runBlocking {
        val name = flowName.value
        val interest = flowInterest.value
        if (name != null && interest != null) {
            try {
                saveCommissionUseCase(Commission(flowId.value, name, interest.toInt()))
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}