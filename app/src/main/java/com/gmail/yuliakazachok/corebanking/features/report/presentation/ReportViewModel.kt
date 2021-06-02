package com.gmail.yuliakazachok.corebanking.features.report.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.report.domain.entities.Report
import com.gmail.yuliakazachok.corebanking.shared.report.domain.usecases.GetReportUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReportViewModel @Inject constructor(
    private val getReportUseCase: GetReportUseCase
) : ViewModel(), EventsDispatcherOwner<ReportViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _listReport = MutableStateFlow<List<Report>?>(null)
    val listReport: Flow<List<Report>>
        get() = _listReport.filterNotNull()

    fun getReport() = viewModelScope.launch {
        try {
            _listReport.value = getReportUseCase()
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }
}