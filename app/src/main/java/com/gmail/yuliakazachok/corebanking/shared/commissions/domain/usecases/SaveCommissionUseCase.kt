package com.gmail.yuliakazachok.corebanking.shared.commissions.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository.CommissionsRepository
import javax.inject.Inject

class SaveCommissionUseCase @Inject constructor(private val repository: CommissionsRepository) {

    suspend operator fun invoke(commission: Commission) = repository.saveCommission(commission)
}