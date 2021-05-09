package com.gmail.yuliakazachok.corebanking.shared.credits.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditDto

interface CreditsDataSource {

    suspend fun getActiveCreditByPassport(numberPassport: Long): CreditDto
}