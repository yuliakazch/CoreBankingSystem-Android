package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository.CommissionsRepository
import javax.inject.Inject

class DeleteCommissionUseCase @Inject constructor(private val repository: CommissionsRepository) {

    suspend operator fun invoke(id: Int) = repository.deleteCommission(id)
}