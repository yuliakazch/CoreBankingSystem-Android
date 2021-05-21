package com.gmail.yuliakazachok.corebanking.shared.users.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenDto(
    @Json(name = "token") @SerializedName("token") val value: String
)