package com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.repository.PaymentsRepository
import javax.inject.Inject

class GetPaymentScheduleUseCase @Inject constructor(private val repository: PaymentsRepository) {

    suspend operator fun invoke(idCredit: Int): List<PaymentSchedule> =
        repository.getPaymentSchedule(idCredit)
}