package com.gmail.yuliakazachok.corebanking.shared.commissions.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.dto.CommissionDto
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission

fun Commission.toDto() = CommissionDto(id, name, interest)

fun List<Commission>.toListDto() = map { it.toDto() }

fun CommissionDto.toEntity() = Commission(id, name, interest)

fun List<CommissionDto>.toListEntity() = map { it.toEntity() }