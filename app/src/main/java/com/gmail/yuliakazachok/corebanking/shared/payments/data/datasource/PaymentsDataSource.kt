package com.gmail.yuliakazachok.corebanking.shared.payments.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentDto
import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentScheduleDto

interface PaymentsDataSource {

    suspend fun getPayments(idCredit: Int): List<PaymentDto>

    suspend fun getPaymentSchedule(idCredit: Int): List<PaymentScheduleDto>
}