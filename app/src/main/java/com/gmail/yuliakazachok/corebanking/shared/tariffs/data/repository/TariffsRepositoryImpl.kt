package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.repository

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.datasource.TariffsDataSource
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.mapper.toDto
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.mapper.toEntity
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository.TariffsRepository
import javax.inject.Inject

class TariffsRepositoryImpl @Inject constructor(
    private val dataSource: TariffsDataSource
) : TariffsRepository {

    override suspend fun getTariffs(): List<Tariff> =
        dataSource.getTariffs().toListEntity()

    override suspend fun getTariffById(id: Int): Tariff =
        dataSource.getTariffById(id).toEntity()

    override suspend fun saveTariff(tariff: Tariff) {
        dataSource.saveTariff(tariff.toDto())
    }

    override suspend fun deleteTariff(id: Int) {
        dataSource.deleteTariff(id)
    }
}