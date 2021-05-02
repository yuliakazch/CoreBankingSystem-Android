package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository.CommissionsRepository
import javax.inject.Inject

class GetCommissionByIdUseCase @Inject constructor(private val repository: CommissionsRepository) {

    suspend operator fun invoke(id: Int): Commission = repository.getCommissionById(id)
}