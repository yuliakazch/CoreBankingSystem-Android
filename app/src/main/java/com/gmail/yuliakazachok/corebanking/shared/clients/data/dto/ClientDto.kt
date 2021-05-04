package com.gmail.yuliakazachok.corebanking.shared.clients.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.Date

@JsonClass(generateAdapter = true)
data class ClientDto(
    @Json(name = "number_passport") @SerializedName("number_passport") val numberPassport: Long,
    @Json(name = "fio") val fio: String,
    @Json(name = "date_birth") @SerializedName("date_birth") val dateBirth: Date,
    @Json(name = "place") val place: String,
    @Json(name = "state")val state: Int,
    @Json(name = "count_block_days") @SerializedName("count_block_days") val countBlockDays: Int
)