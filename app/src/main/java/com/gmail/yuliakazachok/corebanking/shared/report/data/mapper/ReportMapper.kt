package com.gmail.yuliakazachok.corebanking.shared.report.data.mapper

import com.gmail.yuliakazachok.corebanking.shared.report.data.dto.ReportDto
import com.gmail.yuliakazachok.corebanking.shared.report.domain.entities.Report

fun Report.toDto() = ReportDto(date, volumeIssue, percent, totalProfit)

fun List<Report>.toListDto() = map { it.toDto() }

fun ReportDto.toEntity() = Report(date, volumeIssue, percent, totalProfit)

fun List<ReportDto>.toListEntity() = map { it.toEntity() }