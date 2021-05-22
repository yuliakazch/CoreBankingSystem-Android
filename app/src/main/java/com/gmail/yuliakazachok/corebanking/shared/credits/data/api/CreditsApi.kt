package com.gmail.yuliakazachok.corebanking.shared.credits.data.api

import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditCreateDto
import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CreditsApi {

    @GET("/credit/active/{number}")
    suspend fun getActiveCreditByPassport(@Path("number") number: Long): CreditDto

    @GET("/credit/{id}")
    suspend fun getCreditById(@Path("id") id: Int): CreditDto

    @GET("/credit/history/{number}")
    suspend fun getHistoryCredits(@Path("number") number: Long): List<CreditDto>

    @POST("/credit")
    suspend fun saveCredit(@Body creditCreateDto: CreditCreateDto)
}