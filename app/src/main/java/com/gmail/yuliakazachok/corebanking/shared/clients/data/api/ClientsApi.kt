package com.gmail.yuliakazachok.corebanking.shared.clients.data.api

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientsApi {

    @GET("/client/{numberPassport}")
    suspend fun getClientByPassport(@Path("numberPassport") numberPassport: Long): ClientDto
}