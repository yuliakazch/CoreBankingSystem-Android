package com.gmail.yuliakazachok.corebanking.shared.clients.data.repository

import com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource.ClientsDataSource
import com.gmail.yuliakazachok.corebanking.shared.clients.data.mapper.toEntity
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class ClientsRepositoryImpl @Inject constructor(
    private val dataSource: ClientsDataSource
) : ClientsRepository {

    override suspend fun getByPassport(numberPassport: Long): Client =
        dataSource.getClientByPassport(numberPassport).toEntity()
}