package com.gmail.yuliakazachok.corebanking.shared.report.data.api

import com.gmail.yuliakazachok.corebanking.shared.report.data.dto.ReportDto
import retrofit2.http.GET

interface ReportApi {

    @GET("/report")
    suspend fun getReport(): List<ReportDto>
}