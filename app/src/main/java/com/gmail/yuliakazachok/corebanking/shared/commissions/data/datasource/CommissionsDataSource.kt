package com.gmail.yuliakazachok.corebanking.shared.commissions.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto

interface CommissionsDataSource {

    suspend fun getCommissions(): List<CommissionDto>
}