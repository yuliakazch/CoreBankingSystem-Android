package com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities

import java.sql.Date

data class Credit(
    val id: Int = 0,
    val idAvailTariff: Int,
    val dateOpen: Date,
    val term: Int,
    val sum: Int,
    val state: Int
)
