package com.gmail.yuliakazachok.corebanking.shared.clients.data.repository

import com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource.ClientsDataSource
import com.gmail.yuliakazachok.corebanking.shared.clients.data.mapper.toDto
import com.gmail.yuliakazachok.corebanking.shared.clients.data.mapper.toEntity
import com.gmail.yuliakazachok.corebanking.shared.clients.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class ClientsRepositoryImpl @Inject constructor(
    private val dataSource: ClientsDataSource
) : ClientsRepository {

    override suspend fun getByPassport(numberPassport: Long): Client =
        dataSource.getClientByPassport(numberPassport).toEntity()

    override suspend fun searchClients(filters: ClientFilters): List<Client> =
        dataSource.searchClients(filters.toDto()).toListEntity()
}