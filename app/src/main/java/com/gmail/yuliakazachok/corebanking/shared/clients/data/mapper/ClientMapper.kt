package com.gmail.yuliakazachok.corebanking.shared.clients.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientFiltersDto
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters

fun Client.toDto() = ClientDto(numberPassport, fio, dateBirth, place, state, countBlockDays)

fun List<Client>.toListDto() = map { it.toDto() }

fun ClientDto.toEntity() = Client(numberPassport, fio, dateBirth, place, state, countBlockDays)

fun List<ClientDto>.toListEntity() = map { it.toEntity() }

fun ClientFilters.toDto() = ClientFiltersDto(fio, year, state)

fun ClientFiltersDto.toEntity() = ClientFilters(fio, year, state)