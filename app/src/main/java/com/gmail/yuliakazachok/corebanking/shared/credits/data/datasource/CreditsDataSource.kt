package com.gmail.yuliakazachok.corebanking.shared.credits.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditCreateDto
import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditDto

interface CreditsDataSource {

    suspend fun getActiveCreditByPassport(numberPassport: Long): CreditDto

    suspend fun saveCredit(creditCreateDto: CreditCreateDto)
}