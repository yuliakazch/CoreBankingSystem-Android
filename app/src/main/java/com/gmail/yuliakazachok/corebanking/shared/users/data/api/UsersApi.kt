package com.gmail.yuliakazachok.corebanking.shared.users.data.api

import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.CredentialsDto
import com.gmail.yuliakazachok.corebanking.shared.users.data.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {

    @POST("/user/auth")
    suspend fun auth(@Body credentials: CredentialsDto): TokenDto
}