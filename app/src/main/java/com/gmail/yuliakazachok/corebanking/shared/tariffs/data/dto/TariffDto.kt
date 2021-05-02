package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TariffDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "rate") val rate: Int,
    @Json(name = "min_sum") @SerializedName("min_sum") val minSum: Int,
    @Json(name = "max_sum") @SerializedName("max_sum") val maxSum: Int,
    @Json(name = "min_term") @SerializedName("min_term") val minTerm: Int,
    @Json(name = "max_term") @SerializedName("max_term") val maxTerm: Int
)