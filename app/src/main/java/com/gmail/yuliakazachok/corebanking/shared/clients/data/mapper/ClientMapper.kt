package com.gmail.yuliakazachok.corebanking.shared.clients.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.clients.data.dto.ClientDto
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client

fun Client.toDto() = ClientDto(numberPassport, fio, dateBirth, place, isCredit, isTariff, countBlockDays)

fun ClientDto.toEntity() = Client(numberPassport, fio, dateBirth, place, isCredit, isTariff, countBlockDays)