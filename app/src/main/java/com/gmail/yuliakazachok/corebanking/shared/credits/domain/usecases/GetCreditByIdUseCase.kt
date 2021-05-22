package com.gmail.yuliakazachok.corebanking.shared.credits.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.repository.CreditsRepository
import javax.inject.Inject

class GetCreditByIdUseCase @Inject constructor(private val repository: CreditsRepository) {

    suspend operator fun invoke(id: Int): Credit = repository.getCreditById(id)
}