package com.gmail.yuliakazachok.corebanking.shared.report.domain.entities

import java.sql.Date

data class Report(
    val date: Date,
    val volumeIssue: Float,
    val percent: Float,
    val totalProfit: Float
)