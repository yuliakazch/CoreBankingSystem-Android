package com.gmail.yuliakazachok.corebanking.shared.credits.data.api

import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditsApi {

    @GET("/credit/active/{number}")
    suspend fun getActiveCreditByPassport(@Path("number") number: Long): CreditDto
}