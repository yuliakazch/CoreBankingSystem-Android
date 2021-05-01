package com.gmail.yuliakazachok.corebanking.shared.commissions.data.api

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto
import retrofit2.http.*

interface CommissionsApi {

    @GET("/commission")
    suspend fun getCommissions(): List<CommissionDto>

    @GET("/commission/{name}")
    suspend fun getCommissionByName(@Path("name") name: String): CommissionDto

    @POST("/commission")
    suspend fun saveCommission(@Body commissionDto: CommissionDto)

    @DELETE("/commission/{name}")
    suspend fun deleteCommission(@Path("name") name: String)
}