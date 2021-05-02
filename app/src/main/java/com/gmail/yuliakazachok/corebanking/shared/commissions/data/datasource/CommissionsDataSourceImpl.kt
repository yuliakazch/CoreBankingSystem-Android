package com.gmail.yuliakazachok.corebanking.shared.commissions.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.api.CommissionsApi
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto
import javax.inject.Inject

class CommissionsDataSourceImpl @Inject constructor(
    private val api: CommissionsApi
) : CommissionsDataSource {

    override suspend fun getCommissions(): List<CommissionDto> =
        api.getCommissions()

    override suspend fun getCommissionById(id: Int): CommissionDto =
        api.getCommissionById(id)

    override suspend fun saveCommission(commissionDto: CommissionDto) {
        api.saveCommission(commissionDto)
    }

    override suspend fun deleteCommission(id: Int) {
        api.deleteCommission(id)
    }
}