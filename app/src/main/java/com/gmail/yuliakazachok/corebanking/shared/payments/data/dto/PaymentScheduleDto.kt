package com.gmail.yuliakazachok.corebanking.shared.payments.data.dto

import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentScheduleState
import com.squareup.moshi.Json
import java.sql.Date

data class PaymentScheduleDto(
    @Json(name = "date") val date: Date,
    @Json(name = "sum") val sum: Float,
    @Json(name = "state") val state: PaymentScheduleState
)