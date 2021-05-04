package com.gmail.yuliakazachok.corebanking.features.clients.filtersclients.presentation

import androidx.lifecycle.ViewModel
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientStates
import javax.inject.Inject

class FiltersClientsViewModel @Inject constructor() : ViewModel() {

    fun getClientFilters(
        fio: String,
        year: String,
        notTariffChecked: Boolean,
        notCreditChecked: Boolean,
        yesCreditChecked: Boolean,
        lockedChecked: Boolean
    ): ClientFilters {
        return ClientFilters(
            fio = getFio(fio),
            year = getYear(year),
            state = getStates(
                notTariffChecked,
                notCreditChecked,
                yesCreditChecked,
                lockedChecked
            )
        )
    }

    private fun getFio(data: String): String? = if (data.isBlank()) null else data

    private fun getYear(data: String): Int? = if (data.isBlank()) null else data.toInt()

    private fun getStates(
        notTariffChecked: Boolean,
        notCreditChecked: Boolean,
        yesCreditChecked: Boolean,
        lockedChecked: Boolean
    ): List<Int>? {
        val list = mutableListOf<Int>()
        if (notTariffChecked) {
            list.add(ClientStates.STATE_NOT_TARIFF)
        }
        if (notCreditChecked) {
            list.add(ClientStates.STATE_NOT_CREDIT)
        }
        if (yesCreditChecked) {
            list.add(ClientStates.STATE_YES_CREDIT)
        }
        if (lockedChecked) {
            list.add(ClientStates.STATE_BLOCKED)
        }
        return if (list.isEmpty()) null else list
    }
}