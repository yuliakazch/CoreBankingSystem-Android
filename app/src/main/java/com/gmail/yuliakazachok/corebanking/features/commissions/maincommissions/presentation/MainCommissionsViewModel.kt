package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases.GetCommissionsUseCase
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainCommissionsViewModel @Inject constructor(
    private val getCommissionsUseCase: GetCommissionsUseCase
) : ViewModel(), EventsDispatcherOwner<MainCommissionsViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
        fun goToDetailCommission(commission: Commission)
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listCommission = MutableStateFlow<List<Commission>?>(null)
    val listCommission: Flow<List<Commission>>
        get() = _listCommission.filterNotNull()

    init {
        getCommissions()
    }

    fun getCommissions() = viewModelScope.launch {
        try {
            _listCommission.value = getCommissionsUseCase()
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }

    fun goToDetail(commission: Commission) {
        eventsDispatcher.dispatchEvent { goToDetailCommission(commission) }
    }
}