package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.api

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.dto.CommissionDto
import retrofit2.http.GET

interface MainCommissionsApi {

    @GET("/commission")
    suspend fun getCommissions(): List<CommissionDto>
}