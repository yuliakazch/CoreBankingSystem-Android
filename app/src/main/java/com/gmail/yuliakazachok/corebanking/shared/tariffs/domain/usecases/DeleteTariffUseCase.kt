package com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository.TariffsRepository
import javax.inject.Inject

class DeleteTariffUseCase @Inject constructor(private val repository: TariffsRepository) {

    suspend operator fun invoke(id: Int) = repository.deleteTariff(id)
}