package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto.TariffDto

interface TariffsDataSource {

    suspend fun getTariffs(): List<TariffDto>

    suspend fun getTariffById(id: Int): TariffDto

    suspend fun saveTariff(tariffDto: TariffDto)

    suspend fun deleteTariff(id: Int)
}