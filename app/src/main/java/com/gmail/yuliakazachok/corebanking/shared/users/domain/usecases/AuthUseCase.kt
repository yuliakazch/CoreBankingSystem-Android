package com.gmail.yuliakazachok.corebanking.shared.users.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Credentials
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Token
import com.gmail.yuliakazachok.corebanking.shared.users.domain.repository.UsersRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(credentials: Credentials): Token =
        repository.auth(credentials)
}