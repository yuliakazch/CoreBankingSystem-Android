package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto.AvailableTariffDto
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto.TariffDto

interface TariffsDataSource {

    suspend fun getTariffs(): List<TariffDto>

    suspend fun getTariffById(id: Int): TariffDto

    suspend fun getTariffsByPassport(numberPassport: Long): List<TariffDto>

    suspend fun getTariffsNotByPassport(numberPassport: Long): List<TariffDto>

    suspend fun saveTariff(tariffDto: TariffDto)

    suspend fun saveAvailableTariff(availableTariffDto: AvailableTariffDto)

    suspend fun deleteTariff(id: Int)
}