package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.api

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto.TariffDto
import retrofit2.http.*

interface TariffsApi {

    @GET("/tariff")
    suspend fun getTariffs(): List<TariffDto>

    @GET("/tariff/{id}")
    suspend fun getTariffById(@Path("id") id: Int): TariffDto

    @POST("/tariff")
    suspend fun saveTariff(@Body tariffDto: TariffDto)

    @DELETE("/tariff/{id}")
    suspend fun deleteTariff(@Path("id") id: Int)
}