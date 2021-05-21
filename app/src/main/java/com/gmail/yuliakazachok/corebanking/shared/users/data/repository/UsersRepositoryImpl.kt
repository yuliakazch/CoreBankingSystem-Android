package com.gmail.yuliakazachok.corebanking.shared.users.data.repository

import com.gmail.yuliakazachok.corebanking.shared.users.data.datasource.UsersDataSource
import com.gmail.yuliakazachok.corebanking.shared.users.data.mapper.toDto
import com.gmail.yuliakazachok.corebanking.shared.users.data.mapper.toEntity
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Credentials
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Token
import com.gmail.yuliakazachok.corebanking.shared.users.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val dataSource: UsersDataSource
) : UsersRepository {

    override fun isTokenExist(): Boolean = dataSource.isTokenExist()

    override fun getToken(): Token = dataSource.getToken().toEntity()

    override fun saveToken(token: Token) {
        dataSource.saveToken(token.toDto())
    }

    override fun clearToken() {
        dataSource.clearToken()
    }

    override suspend fun auth(credentials: Credentials): Token =
        dataSource.auth(credentials.toDto()).toEntity()
}