package com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientFiltersDto

interface ClientsDataSource {

    suspend fun getClientByPassport(numberPassport: Long): ClientDto

    suspend fun blockClient(number: Long, days: Int)

    suspend fun unblockClient(number: Long)

    suspend fun searchClients(filters: ClientFiltersDto): List<ClientDto>

    suspend fun deleteClient(numberPassport: Long)
}