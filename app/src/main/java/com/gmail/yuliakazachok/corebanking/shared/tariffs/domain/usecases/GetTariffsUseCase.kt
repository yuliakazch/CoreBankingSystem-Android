package com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository.TariffsRepository
import javax.inject.Inject

class GetTariffsUseCase @Inject constructor(private val repository: TariffsRepository) {

    suspend operator fun invoke(): List<Tariff> = repository.getTariffs()
}