package com.gmail.yuliakazachok.corebanking.shared.commissions.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.api.CommissionsApi
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto
import javax.inject.Inject

class CommissionsDataSourceImpl @Inject constructor(
    private val api: CommissionsApi
) : CommissionsDataSource {

    override suspend fun getCommissions(): List<CommissionDto> =
        api.getCommissions()
}