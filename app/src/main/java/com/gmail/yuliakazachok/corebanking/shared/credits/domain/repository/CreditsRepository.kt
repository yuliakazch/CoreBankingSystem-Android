package com.gmail.yuliakazachok.corebanking.shared.credits.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditCreate

interface CreditsRepository {

    suspend fun getActiveCreditByPassport(numberPassport: Long): Credit

    suspend fun getCreditById(id: Int): Credit

    suspend fun getHistoryCredits(numberPassport: Long): List<Credit>

    suspend fun saveCredit(creditCreate: CreditCreate)
}