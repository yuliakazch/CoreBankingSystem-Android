package com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.AvailableTariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository.TariffsRepository
import javax.inject.Inject

class SaveAvailableTariffUseCase @Inject constructor(private val repository: TariffsRepository) {

    suspend operator fun invoke(availableTariff: AvailableTariff) =
        repository.saveAvailableTariff(availableTariff)
}