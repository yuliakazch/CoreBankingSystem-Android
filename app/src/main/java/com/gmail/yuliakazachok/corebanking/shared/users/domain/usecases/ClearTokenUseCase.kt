package com.gmail.yuliakazachok.corebanking.shared.users.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.users.domain.repository.UsersRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke() = repository.clearToken()
}