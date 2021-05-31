package com.gmail.yuliakazachok.corebanking.shared.payments.data.repository

import com.gmail.yuliakazachok.corebanking.shared.payments.data.datasource.PaymentsDataSource
import com.gmail.yuliakazachok.corebanking.shared.payments.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.repository.PaymentsRepository
import javax.inject.Inject

class PaymentsRepositoryImpl @Inject constructor(
    private val dataSource: PaymentsDataSource
) : PaymentsRepository {

    override suspend fun getPayments(idCredit: Int): List<Payment> =
        dataSource.getPayments(idCredit).toListEntity()

    override suspend fun getPaymentSchedule(idCredit: Int): List<PaymentSchedule> =
        dataSource.getPaymentSchedule(idCredit).toListEntity()
}