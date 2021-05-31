package com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities

import java.sql.Date

data class Payment(
    val date: Date,
    val sum: Float,
    val interest: Int
)