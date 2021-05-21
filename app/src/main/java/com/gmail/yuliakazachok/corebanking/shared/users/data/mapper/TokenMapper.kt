package com.gmail.yuliakazachok.corebanking.shared.users.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.TokenDto
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Token

fun Token.toDto() = TokenDto(value)

fun TokenDto.toEntity() = Token(value)