package com.gmail.yuliakazachok.corebanking.shared.credits.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.credits.data.dto.CreditCreateDto
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditCreate

fun CreditCreate.toDto() = CreditCreateDto(numberPassport, idTariff, term, sum, dateOpen)

fun CreditCreateDto.toEntity() = CreditCreate(numberPassport, idTariff, term, sum, dateOpen)