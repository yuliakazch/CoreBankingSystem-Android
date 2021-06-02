package com.gmail.yuliakazachok.corebanking.shared.report.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.Date

@JsonClass(generateAdapter = true)
data class ReportDto(
    @Json(name = "date") val date: Date,
    @Json(name = "volume_issue") @SerializedName("volume_issue") val volumeIssue: Float,
    @Json(name = "percent") val percent: Float,
    @Json(name = "total_profit") @SerializedName("total_profit") val totalProfit: Float
)
