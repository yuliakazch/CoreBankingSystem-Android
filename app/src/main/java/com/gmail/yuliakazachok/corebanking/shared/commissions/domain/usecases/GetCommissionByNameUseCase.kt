package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository.CommissionsRepository
import javax.inject.Inject

class GetCommissionByNameUseCase @Inject constructor(private val repository: CommissionsRepository) {

    suspend operator fun invoke(name: String): Commission = repository.getCommissionByName(name)
}