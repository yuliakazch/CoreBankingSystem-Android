package com.gmail.yuliakazachok.corebanking.shared.report.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.report.data.api.ReportApi
import com.gmail.yuliakazachok.corebanking.shared.report.data.dto.ReportDto
import javax.inject.Inject

class ReportDataSourceImpl @Inject constructor(
    private val api: ReportApi
) : ReportDataSource {

    override suspend fun getReport(): List<ReportDto> =
        api.getReport()
}