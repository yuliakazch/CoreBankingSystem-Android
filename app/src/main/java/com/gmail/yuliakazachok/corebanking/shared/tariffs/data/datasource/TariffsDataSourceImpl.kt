package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.api.TariffsApi
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto.TariffDto
import javax.inject.Inject

class TariffsDataSourceImpl @Inject constructor(
    private val api: TariffsApi
) : TariffsDataSource {

    override suspend fun getTariffs(): List<TariffDto> =
        api.getTariffs()

    override suspend fun getTariffById(id: Int): TariffDto =
        api.getTariffById(id)

    override suspend fun getTariffsByPassport(numberPassport: Long): List<TariffDto> =
        api.getTariffsByPassport(numberPassport)

    override suspend fun getTariffsNotByPassport(numberPassport: Long): List<TariffDto> =
        api.getTariffsNotByPassport(numberPassport)

    override suspend fun saveTariff(tariffDto: TariffDto) {
        api.saveTariff(tariffDto)
    }

    override suspend fun deleteTariff(id: Int) {
        api.deleteTariff(id)
    }
}