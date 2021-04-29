package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.datasource

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.api.MainCommissionsApi
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.dto.CommissionDto
import javax.inject.Inject

class MainCommissionsDataSourceImpl @Inject constructor(
    private val api: MainCommissionsApi
) : MainCommissionsDataSource {

    override suspend fun getCommissions(): List<CommissionDto> =
        api.getCommissions()
}