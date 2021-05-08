package com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.AvailableTariff
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff

interface TariffsRepository {

    suspend fun getTariffs(): List<Tariff>

    suspend fun getTariffById(id: Int): Tariff

    suspend fun getTariffsByPassport(numberPassport: Long): List<Tariff>

    suspend fun getTariffsNotByPassport(numberPassport: Long): List<Tariff>

    suspend fun saveTariff(tariff: Tariff)

    suspend fun saveAvailableTariff(availableTariff: AvailableTariff)

    suspend fun deleteTariff(id: Int)
}