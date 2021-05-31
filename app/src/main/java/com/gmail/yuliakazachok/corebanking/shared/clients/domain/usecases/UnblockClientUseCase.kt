package com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class UnblockClientUseCase @Inject constructor(private val repository: ClientsRepository) {

    suspend operator fun invoke(numberPassport: Long) =
        repository.unblockClient(numberPassport)
}