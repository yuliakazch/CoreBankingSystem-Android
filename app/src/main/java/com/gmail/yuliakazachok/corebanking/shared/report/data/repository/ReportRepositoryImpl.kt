package com.gmail.yuliakazachok.corebanking.shared.report.data.repository

import com.gmail.yuliakazachok.corebanking.shared.report.data.datasource.ReportDataSource
import com.gmail.yuliakazachok.corebanking.shared.report.data.mapper.toListEntity
import com.gmail.yuliakazachok.corebanking.shared.report.domain.entities.Report
import com.gmail.yuliakazachok.corebanking.shared.report.domain.repository.ReportRepository
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val dataSource: ReportDataSource
) : ReportRepository {

    override suspend fun getReport(): List<Report> =
        dataSource.getReport().toListEntity()
}