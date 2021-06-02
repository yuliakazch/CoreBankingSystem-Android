package com.gmail.yuliakazachok.corebanking.shared.report.domain.usecases

import com.gmail.yuliakazachok.corebanking.shared.report.domain.repository.ReportRepository
import javax.inject.Inject

class GetReportUseCase @Inject constructor(private val repository: ReportRepository) {

    suspend operator fun invoke() =
        repository.getReport()
}