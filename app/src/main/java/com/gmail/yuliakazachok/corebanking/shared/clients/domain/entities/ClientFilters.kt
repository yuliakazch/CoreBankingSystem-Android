package com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities

import java.io.Serializable

data class ClientFilters(
    val fio: String?,
    val year: Int?,
    val state: List<ClientState>?
) : Serializable