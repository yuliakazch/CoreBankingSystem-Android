package com.gmail.yuliakazachok.corebanking.shared.users.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Token
import com.gmail.yuliakazachok.corebanking.shared.users.domain.repository.UsersRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(token: Token) = repository.saveToken(token)
}