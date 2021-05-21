package com.gmail.yuliakazachok.corebanking.shared.users.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenDto(
    @Json(name = "login") val value: String
)