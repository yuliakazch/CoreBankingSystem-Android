package com.gmail.yuliakazachok.corebanking.shared.credits.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.Date

@JsonClass(generateAdapter = true)
data class CreditCreateDto(
    @Json(name = "number_passport") @SerializedName("number_passport") val numberPassport: Long,
    @Json(name = "id_tariff") @SerializedName("id_tariff") val idTariff: Int,
    @Json(name = "term") val term: Int,
    @Json(name = "sum") val sum: Int,
    @Json(name = "date_open") @SerializedName("date_open") val dateOpen: Date
)