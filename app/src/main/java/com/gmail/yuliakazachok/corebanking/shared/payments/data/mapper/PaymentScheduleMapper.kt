package com.gmail.yuliakazachok.corebanking.shared.payments.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentScheduleDto
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule

fun PaymentSchedule.toDto() = PaymentScheduleDto(date, sum, state)

fun List<PaymentSchedule>.toListDto() = map { it.toDto() }

fun PaymentScheduleDto.toEntity() = PaymentSchedule(date, sum, state)

fun List<PaymentScheduleDto>.toListEntity() = map { it.toEntity() }