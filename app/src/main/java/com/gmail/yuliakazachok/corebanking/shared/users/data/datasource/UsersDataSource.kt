package com.gmail.yuliakazachok.corebanking.shared.users.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.CredentialsDto
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.TokenDto

interface UsersDataSource {

    suspend fun auth(credentials: CredentialsDto): TokenDto
}