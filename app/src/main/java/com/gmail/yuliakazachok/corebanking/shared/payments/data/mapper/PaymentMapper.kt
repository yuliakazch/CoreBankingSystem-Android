package com.gmail.yuliakazachok.corebanking.shared.payments.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentDto
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment

fun Payment.toDto() = PaymentDto(date, sum, interest)

fun List<Payment>.toListDto() = map { it.toDto() }

fun PaymentDto.toEntity() = Payment(date, sum, interest)

fun List<PaymentDto>.toListEntity() = map { it.toEntity() }