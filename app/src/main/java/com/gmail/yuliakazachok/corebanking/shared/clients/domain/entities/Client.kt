package com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities

import java.sql.Date

data class Client(
    val numberPassport: Long,
    val fio: String,
    val dateBirth: Date,
    val place: String,
    val isCredit: Boolean,
    val isTariff: Boolean,
    val countBlockDays: Int
)