package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommissionDto(
    @Json(name = "name") val name: String,
    @Json(name = "interest") val interest: Int
)