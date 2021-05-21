package com.gmail.yuliakazachok.corebanking.shared.users.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.CredentialsDto
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.TokenDto

interface UsersDataSource {

    fun isTokenExist(): Boolean

    fun getToken(): TokenDto

    fun saveToken(tokenDto: TokenDto)

    fun clearToken()

    suspend fun auth(credentials: CredentialsDto): TokenDto
}