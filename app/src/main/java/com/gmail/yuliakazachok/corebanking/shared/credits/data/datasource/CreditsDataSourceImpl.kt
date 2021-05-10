package com.gmail.yuliakazachok.corebanking.shared.credits.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.credits.data.api.CreditsApi
import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditCreateDto
import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditDto
import javax.inject.Inject

class CreditsDataSourceImpl @Inject constructor(
    private val api: CreditsApi
) : CreditsDataSource {

    override suspend fun getActiveCreditByPassport(numberPassport: Long): CreditDto =
        api.getActiveCreditByPassport(numberPassport)

    override suspend fun saveCredit(creditCreateDto: CreditCreateDto) =
        api.saveCredit(creditCreateDto)
}