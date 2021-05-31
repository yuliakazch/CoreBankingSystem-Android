package com.gmail.yuliakazachok.corebanking.shared.payments.data.dto

import com.squareup.moshi.Json
import java.sql.Date

data class PaymentDto(
    @Json(name = "date") val date: Date,
    @Json(name = "sum") val sum: Float,
    @Json(name = "interest") val interest: Int
)