package com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities

import java.sql.Date

data class PaymentCreate(
    val date: Date,
    val sum: Float,
    val idCommission: Int
)