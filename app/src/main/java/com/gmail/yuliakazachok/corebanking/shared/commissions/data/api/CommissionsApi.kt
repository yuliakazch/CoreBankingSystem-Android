package com.gmail.yuliakazachok.corebanking.shared.commissions.data.api

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto
import retrofit2.http.GET

interface CommissionsApi {

    @GET("/commission")
    suspend fun getCommissions(): List<CommissionDto>
}