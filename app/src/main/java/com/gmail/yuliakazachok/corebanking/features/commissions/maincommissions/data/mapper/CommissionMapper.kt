package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.mapper

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.dto.CommissionDto
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.entities.Commission

fun Commission.toDto() = CommissionDto(name, interest)

fun List<Commission>.toListDto() = map { it.toDto() }

fun CommissionDto.toEntity() = Commission(name, interest)

fun List<CommissionDto>.toListEntity() = map { it.toEntity() }