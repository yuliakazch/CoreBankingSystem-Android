package com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.repository.PaymentsRepository
import javax.inject.Inject

class GetPaymentsUseCase @Inject constructor(private val repository: PaymentsRepository) {

    suspend operator fun invoke(idCredit: Int): List<Payment> =
        repository.getPayments(idCredit)
}