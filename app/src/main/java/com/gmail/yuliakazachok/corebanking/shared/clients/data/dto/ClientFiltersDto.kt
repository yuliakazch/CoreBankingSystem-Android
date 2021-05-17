package com.gmail.yuliakazachok.corebanking.shared.clients.data.dto

import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientState
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClientFiltersDto(
    @Json(name = "fio") val fio: String?,
    @Json(name = "year") val year: Int?,
    @Json(name = "state") val state: List<ClientState>?
)