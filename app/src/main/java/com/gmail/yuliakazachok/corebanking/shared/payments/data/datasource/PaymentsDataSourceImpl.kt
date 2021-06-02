package com.gmail.yuliakazachok.corebanking.shared.payments.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.payments.data.api.PaymentsApi
import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentCreateDto
import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentDto
import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentScheduleDto
import javax.inject.Inject

class PaymentsDataSourceImpl @Inject constructor(
    private val api: PaymentsApi
) : PaymentsDataSource {

    override suspend fun getPayments(idCredit: Int): List<PaymentDto> =
        api.getPayments(idCredit)

    override suspend fun getPaymentSchedule(idCredit: Int): List<PaymentScheduleDto> =
        api.getPaymentSchedule(idCredit)

    override suspend fun makePayment(paymentCreateDto: PaymentCreateDto) =
        api.makePayment(paymentCreateDto)
}