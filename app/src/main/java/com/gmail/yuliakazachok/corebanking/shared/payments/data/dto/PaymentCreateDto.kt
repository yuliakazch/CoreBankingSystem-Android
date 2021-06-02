package com.gmail.yuliakazachok.corebanking.shared.payments.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.sql.Date

data class PaymentCreateDto(
    @Json(name = "id_credit") @SerializedName("id_credit") val idCredit: Int,
    @Json(name = "date") val date: Date,
    @Json(name = "sum") val sum: Float,
    @Json(name = "id_commission") @SerializedName("id_commission") val idCommission: Int
)