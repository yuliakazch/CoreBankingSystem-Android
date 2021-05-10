package com.gmail.yuliakazachok.corebanking.shared.credits.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditCreate
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.repository.CreditsRepository
import javax.inject.Inject

class SaveCreditUseCase @Inject constructor(private val repository: CreditsRepository) {

    suspend operator fun invoke(creditCreate: CreditCreate) =
        repository.saveCredit(creditCreate)
}