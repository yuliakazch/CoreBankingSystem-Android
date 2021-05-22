package com.gmail.yuliakazachok.corebanking.features.credits.listcredits.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.usecases.GetHistoryCreditsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListCreditsViewModel @Inject constructor(
    private val getHistoryCreditsUseCase: GetHistoryCreditsUseCase
) : ViewModel(), EventsDispatcherOwner<ListCreditsViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
        fun goToDetailCredit(id: Int)
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listCredits = MutableStateFlow<List<Credit>?>(null)
    val listCredits: Flow<List<Credit>>
        get() = _listCredits.filterNotNull()

    fun getHistoryCredits(numberPassport: Long?) = viewModelScope.launch {
        numberPassport?.let {
            try {
                _listCredits.value = getHistoryCreditsUseCase(it)
            } catch (throwable: Throwable) {
                eventsDispatcher.dispatchEvent { showToastError() }
            }
        }
    }

    fun goToDetail(id: Int) {
        eventsDispatcher.dispatchEvent { goToDetailCredit(id) }
    }
}