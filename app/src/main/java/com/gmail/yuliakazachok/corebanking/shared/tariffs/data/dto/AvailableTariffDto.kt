package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableTariffDto(
    @Json(name = "id") val id: Int,
    @Json(name = "id_tariff") @SerializedName("id_tariff") val idTariff: Int,
    @Json(name = "number_passport") @SerializedName("number_passport") val numberPassport: Long
)
