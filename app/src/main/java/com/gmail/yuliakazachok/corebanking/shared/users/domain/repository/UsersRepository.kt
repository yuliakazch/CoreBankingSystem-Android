package com.gmail.yuliakazachok.corebanking.shared.users.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Credentials
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Token

interface UsersRepository {

    suspend fun auth(credentials: Credentials): Token
}