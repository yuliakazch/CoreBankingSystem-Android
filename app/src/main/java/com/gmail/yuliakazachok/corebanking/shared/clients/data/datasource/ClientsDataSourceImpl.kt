package com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.clients.data.api.ClientsApi
import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientFiltersDto
import javax.inject.Inject

class ClientsDataSourceImpl @Inject constructor(
    private val api: ClientsApi
) : ClientsDataSource {

    override suspend fun getClientByPassport(numberPassport: Long): ClientDto =
        api.getClientByPassport(numberPassport)

    override suspend fun searchClients(filters: ClientFiltersDto): List<ClientDto> =
        api.searchClients(filters)
}