package com.gmail.yuliakazachok.corebanking.shared.payments.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentCreate
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.repository.PaymentsRepository
import javax.inject.Inject

class MakePayment @Inject constructor(private val repository: PaymentsRepository) {

    suspend operator fun invoke(paymentCreate: PaymentCreate) =
        repository.makePayment(paymentCreate)
}