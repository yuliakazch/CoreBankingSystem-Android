package com.gmail.yuliakazachok.corebanking.shared.credits.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit

interface CreditsRepository {

    suspend fun getActiveCreditByPassport(numberPassport: Long): Credit
}