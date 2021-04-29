package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.usecases

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.repository.MainCommissionsRepository
import javax.inject.Inject

class GetCommissionsUseCase @Inject constructor(private val repository: MainCommissionsRepository) {

    suspend operator fun invoke(): List<Commission> = repository.getCommissions()
}