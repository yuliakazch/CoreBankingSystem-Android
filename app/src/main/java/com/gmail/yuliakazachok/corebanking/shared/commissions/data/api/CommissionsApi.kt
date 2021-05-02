package com.gmail.yuliakazachok.corebanking.shared.commissions.data.api

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto
import retrofit2.http.*

interface CommissionsApi {

    @GET("/commission")
    suspend fun getCommissions(): List<CommissionDto>

    @GET("/commission/{id}")
    suspend fun getCommissionById(@Path("id") id: Int): CommissionDto

    @POST("/commission")
    suspend fun saveCommission(@Body commissionDto: CommissionDto)

    @DELETE("/commission/{id}")
    suspend fun deleteCommission(@Path("id") id: Int)
}