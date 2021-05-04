package com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class BlockClientUseCase @Inject constructor(private val repository: ClientsRepository) {

    suspend operator fun invoke(number: Long, days: Int) =
        repository.blockClient(number, days)
}