package com.gmail.yuliakazachok.corebanking.shared.report.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.report.domain.entities.Report

interface ReportRepository {

    suspend fun getReport(): List<Report>
}