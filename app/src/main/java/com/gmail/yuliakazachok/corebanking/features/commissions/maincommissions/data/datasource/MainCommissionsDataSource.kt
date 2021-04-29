package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.datasource

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.dto.CommissionDto

interface MainCommissionsDataSource {

    suspend fun getCommissions(): List<CommissionDto>
}