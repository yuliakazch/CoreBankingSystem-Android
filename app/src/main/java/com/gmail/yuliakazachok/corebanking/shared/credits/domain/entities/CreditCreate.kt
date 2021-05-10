package com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities

import java.sql.Date

data class CreditCreate (
    val numberPassport: Long,
    val idTariff: Int,
    val term: Int,
    val sum: Int,
    val dateOpen: Date
)