package com.gmail.yuliakazachok.corebanking.shared.payments.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentCreateDto
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentCreate

fun PaymentCreate.toDto() = PaymentCreateDto(idCredit, date, sum, idCommission)

fun PaymentCreateDto.toEntity() = PaymentCreate(idCredit, date, sum, idCommission)