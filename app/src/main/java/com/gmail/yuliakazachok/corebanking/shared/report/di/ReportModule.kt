package com.gmail.yuliakazachok.corebanking.shared.report.di

import com.gmail.yuliakazachok.corebanking.shared.report.data.api.ReportApi
import com.gmail.yuliakazachok.corebanking.shared.report.data.datasource.ReportDataSource
import com.gmail.yuliakazachok.corebanking.shared.report.data.datasource.ReportDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.report.data.repository.ReportRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.report.domain.repository.ReportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object ReportModule {

    @Provides
    fun provideReportRepository(
        reportRepositoryImpl: ReportRepositoryImpl
    ): ReportRepository = reportRepositoryImpl

    @Provides
    fun provideReportDataSource(
        reportDataSourceImpl: ReportDataSourceImpl
    ): ReportDataSource = reportDataSourceImpl

    @Provides
    fun provideReportApi(retrofit: Retrofit) =
        retrofit.create(ReportApi::class.java)
}