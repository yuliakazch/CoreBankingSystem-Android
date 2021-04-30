package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities

import java.io.Serializable

data class Commission(
    val name: String,
    val interest: Int
) : Serializable