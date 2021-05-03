package com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class SearchClientsUseCase @Inject constructor(private val repository: ClientsRepository) {

    suspend operator fun invoke(filters: ClientFilters): List<Client> =
        repository.searchClients(filters)
}