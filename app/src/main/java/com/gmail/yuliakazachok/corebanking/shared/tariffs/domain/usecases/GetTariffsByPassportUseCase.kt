package com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository.TariffsRepository
import javax.inject.Inject

class GetTariffsByPassportUseCase @Inject constructor(private val repository: TariffsRepository) {

    suspend operator fun invoke(numberPassport: Long): List<Tariff> =
        repository.getTariffsByPassport(numberPassport)
}