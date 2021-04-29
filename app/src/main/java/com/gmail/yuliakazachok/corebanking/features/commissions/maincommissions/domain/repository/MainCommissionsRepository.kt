package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.repository

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.entities.Commission

interface MainCommissionsRepository {

    suspend fun getCommissions(): List<Commission>
}