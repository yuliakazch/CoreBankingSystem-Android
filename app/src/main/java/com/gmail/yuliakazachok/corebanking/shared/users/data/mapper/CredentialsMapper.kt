package com.gmail.yuliakazachok.corebanking.shared.users.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.CredentialsDto
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Credentials

fun Credentials.toDto() = CredentialsDto(login, password)

fun CredentialsDto.toEntity() = Credentials(login, password)