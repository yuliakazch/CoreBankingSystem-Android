package com.gmail.yuliakazachok.corebanking.shared.commissions.data.repository

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.datasource.CommissionsDataSource
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.mapper.toDto
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.mapper.toEntity
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository.CommissionsRepository
import javax.inject.Inject

class CommissionsRepositoryImpl @Inject constructor(
    private val dataSource: CommissionsDataSource
) : CommissionsRepository {

    override suspend fun getCommissions(): List<Commission> =
        dataSource.getCommissions().toListEntity()

    override suspend fun getCommissionById(id: Int): Commission =
        dataSource.getCommissionById(id).toEntity()

    override suspend fun saveCommission(commission: Commission) {
        dataSource.saveCommission(commission.toDto())
    }

    override suspend fun deleteCommission(id: Int) {
        dataSource.deleteCommission(id)
    }
}