package com.gmail.yuliakazachok.corebanking.shared.users.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CredentialsDto(
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String
)