package com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities

import java.sql.Date

data class Credit(
    val id: Int = 0,
    val numberPassport: Long,
    val dateOpen: Date,
    val rate: Int,
    val term: Int,
    val sum: Int,
    val state: Int
)
