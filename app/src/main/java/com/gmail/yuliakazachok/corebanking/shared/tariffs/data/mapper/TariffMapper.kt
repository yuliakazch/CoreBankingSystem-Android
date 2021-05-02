package com.gmail.yuliakazachok.corebanking.shared.tariffs.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.dto.TariffDto
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff


fun Tariff.toDto() = TariffDto(id, name, rate, minSum, maxSum, minTerm, maxTerm)

fun List<Tariff>.toListDto() = map { it.toDto() }

fun TariffDto.toEntity() = Tariff(id, name, rate, minSum, maxSum, minTerm, maxTerm)

fun List<TariffDto>.toListEntity() = map { it.toEntity() }