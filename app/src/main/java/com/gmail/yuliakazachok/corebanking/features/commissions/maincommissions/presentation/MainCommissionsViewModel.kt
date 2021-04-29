package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.usecases.GetCommissionsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainCommissionsViewModel @Inject constructor(
    private val getCommissionsUseCase: GetCommissionsUseCase
) : ViewModel() {

    private val _listCommission = MutableStateFlow<List<Commission>?>(null)
    val listCommission: Flow<List<Commission>>
        get() = _listCommission.filterNotNull()

    init {
        getCommissions()
    }

    fun getCommissions() = viewModelScope.launch {
        _listCommission.value = getCommissionsUseCase()
    }
}