package com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities

import java.io.Serializable

data class Tariff(
    val id: Int = 0,
    val name: String,
    val rate: Int,
    val minSum: Int,
    val maxSum: Int,
    val minTerm: Int,
    val maxTerm: Int
) : Serializable