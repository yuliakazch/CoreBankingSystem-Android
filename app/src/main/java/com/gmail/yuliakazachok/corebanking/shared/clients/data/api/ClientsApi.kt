package com.gmail.yuliakazachok.corebanking.shared.clients.data.api

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientFiltersDto
import retrofit2.http.*

interface ClientsApi {

    @GET("/client/{numberPassport}")
    suspend fun getClientByPassport(@Path("numberPassport") numberPassport: Long): ClientDto

    @GET("/client/block")
    suspend fun blockClient(@Query("number") number: Long, @Query("days") days: Int)

    @POST("/client/search")
    suspend fun searchClients(@Body filters: ClientFiltersDto): List<ClientDto>

    @DELETE("/client/{numberPassport}")
    suspend fun deleteClient(@Path("numberPassport") numberPassport: Long)
}