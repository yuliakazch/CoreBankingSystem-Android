package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission

interface CommissionsRepository {

    suspend fun getCommissions(): List<Commission>

    suspend fun saveCommission(commission: Commission)

    suspend fun deleteCommission(name: String)
}