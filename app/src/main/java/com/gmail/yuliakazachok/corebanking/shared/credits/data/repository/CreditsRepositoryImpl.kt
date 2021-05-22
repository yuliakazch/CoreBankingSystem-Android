package com.gmail.yuliakazachok.corebanking.shared.credits.data.repository

import com.gmail.yuliakazachok.corebanking.shared.credits.data.datasource.CreditsDataSource
import com.gmail.yuliakazachok.corebanking.shared.credits.data.mapper.toDto
import com.gmail.yuliakazachok.corebanking.shared.credits.data.mapper.toEntity
import com.gmail.yuliakazachok.corebanking.shared.credits.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditCreate
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.repository.CreditsRepository
import javax.inject.Inject

class CreditsRepositoryImpl @Inject constructor(
    private val dataSource: CreditsDataSource
) : CreditsRepository {

    override suspend fun getActiveCreditByPassport(numberPassport: Long): Credit =
        dataSource.getActiveCreditByPassport(numberPassport).toEntity()

    override suspend fun getCreditById(id: Int): Credit =
        dataSource.getCreditById(id).toEntity()

    override suspend fun getHistoryCredits(numberPassport: Long): List<Credit> =
        dataSource.getHistoryCredits(numberPassport).toListEntity()

    override suspend fun saveCredit(creditCreate: CreditCreate) =
        dataSource.saveCredit(creditCreate.toDto())
}