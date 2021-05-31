package com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters

interface ClientsRepository {

    suspend fun getByPassport(numberPassport: Long): Client

    suspend fun blockClient(number: Long, days: Int)

    suspend fun unblockClient(number: Long)

    suspend fun searchClients(filters: ClientFilters): List<Client>

    suspend fun deleteClient(numberPassport: Long)
}