package com.gmail.yuliakazachok.corebanking.shared.clients.data.api

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientFiltersDto
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientsApi {

    @GET("/client/{numberPassport}")
    suspend fun getClientByPassport(@Path("numberPassport") numberPassport: Long): ClientDto

    @POST("client/search")
    suspend fun searchClients(@Body filters: ClientFiltersDto): List<ClientDto>
}