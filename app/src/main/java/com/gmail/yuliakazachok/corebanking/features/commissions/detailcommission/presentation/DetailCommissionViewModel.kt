package com.gmail.yuliakazachok.corebanking.features.commissions.detailcommission.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.DeleteCommissionUseCase
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.GetCommissionByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailCommissionViewModel @Inject constructor(
    private val getCommissionByIdUseCase: GetCommissionByIdUseCase,
    private val deleteCommissionUseCase: DeleteCommissionUseCase
) : ViewModel(), EventsDispatcherOwner<DetailCommissionViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _commission = MutableStateFlow<Commission?>(null)
    val commission: Flow<Commission>
        get() = _commission.filterNotNull()

    fun getCommissionById(idCommission: Int?) = viewModelScope.launch {
        idCommission?.let {
            try {
                _commission.value = getCommissionByIdUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun deleteCommission() = viewModelScope.launch {
        _commission.value?.let {
            try {
                deleteCommissionUseCase(it.id)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }
}