package com.gmail.yuliakazachok.corebanking.shared.users.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.users.data.api.UsersApi
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.CredentialsDto
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.TokenDto
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val api: UsersApi
) : UsersDataSource {

    override suspend fun auth(credentials: CredentialsDto): TokenDto =
        api.auth(credentials)
}