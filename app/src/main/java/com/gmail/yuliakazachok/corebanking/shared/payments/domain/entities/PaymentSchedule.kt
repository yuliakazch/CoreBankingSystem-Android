package com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities

import java.sql.Date

data class PaymentSchedule(
    val date: Date,
    val sum: Float,
    val state: PaymentScheduleState
)