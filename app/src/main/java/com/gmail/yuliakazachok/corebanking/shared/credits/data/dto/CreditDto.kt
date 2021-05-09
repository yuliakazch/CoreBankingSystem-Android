package com.gmail.yuliakazachok.corebanking.shared.credits.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.Date

@JsonClass(generateAdapter = true)
data class CreditDto(
    @Json(name = "id") val id: Int,
    @Json(name = "id_avail_tariff") @SerializedName("id_avail_tariff") val idAvailTariff: Int,
    @Json(name = "date_open") @SerializedName("date_open") val dateOpen: Date,
    @Json(name = "term") val term: Int,
    @Json(name = "sum") val sum: Int,
    @Json(name = "state") val state: Int
)
