package com.gmail.yuliakazachok.corebanking.shared.payments.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule

interface PaymentsRepository {

    suspend fun getPayments(idCredit: Int): List<Payment>

    suspend fun getPaymentSchedule(idCredit: Int): List<PaymentSchedule>
}