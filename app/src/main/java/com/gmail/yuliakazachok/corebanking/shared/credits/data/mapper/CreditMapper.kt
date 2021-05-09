package com.gmail.yuliakazachok.corebanking.shared.credits.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditDto
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit

fun Credit.toDto() = CreditDto(id, numberPassport, dateOpen, rate, term, sum, state)

fun List<Credit>.toListDto() = map { it.toDto() }

fun CreditDto.toEntity() = Credit(id, numberPassport, dateOpen, rate, term, sum, state)

fun List<CreditDto>.toListEntity() = map { it.toEntity() }