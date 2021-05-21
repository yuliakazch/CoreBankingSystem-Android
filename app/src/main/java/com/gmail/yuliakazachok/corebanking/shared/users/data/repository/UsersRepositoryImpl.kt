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

    override suspend fun auth(credentials: Credentials): Token =
        dataSource.auth(credentials.toDto()).toEntity()
}