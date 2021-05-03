package com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto

interface ClientsDataSource {

    suspend fun getClientByPassport(numberPassport: Long): ClientDto
}