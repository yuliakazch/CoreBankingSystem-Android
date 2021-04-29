package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.repository

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.datasource.MainCommissionsDataSource
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.repository.MainCommissionsRepository
import javax.inject.Inject

class MainCommissionsRepositoryImpl @Inject constructor(
    private val dataSource: MainCommissionsDataSource
) : MainCommissionsRepository {

    override suspend fun getCommissions(): List<Commission> =
        dataSource.getCommissions().toListEntity()
}