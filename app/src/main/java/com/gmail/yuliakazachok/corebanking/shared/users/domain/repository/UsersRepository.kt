package com.gmail.yuliakazachok.corebanking.shared.users.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Credentials
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Token

interface UsersRepository {

    fun isTokenExist(): Boolean

    fun getToken(): Token

    fun saveToken(token: Token)

    fun clearToken()

    suspend fun auth(credentials: Credentials): Token
}