package com.gmail.yuliakazachok.corebanking.shared.report.data.datasource

import com.gmail.yuliakazachok.corebanking.shared.report.data.dto.ReportDto

interface ReportDataSource {

    suspend fun getReport(): List<ReportDto>
}