package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities

import java.io.Serializable

data class Commission(
    val id: Int = 0,
    val name: String,
    val interest: Int
) : Serializable