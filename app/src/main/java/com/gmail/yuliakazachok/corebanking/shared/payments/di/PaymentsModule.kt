package com.gmail.yuliakazachok.corebanking.shared.payments.di

import com.gmail.yuliakazachok.corebanking.shared.payments.data.api.PaymentsApi
import com.gmail.yuliakazachok.corebanking.shared.payments.data.datasource.PaymentsDataSource
import com.gmail.yuliakazachok.corebanking.shared.payments.data.datasource.PaymentsDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.payments.data.repository.PaymentsRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.repository.PaymentsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object PaymentsModule {

    @Provides
    fun providePaymentsRepository(
        paymentsRepositoryImpl: PaymentsRepositoryImpl
    ): PaymentsRepository = paymentsRepositoryImpl

    @Provides
    fun providePaymentsDataSource(
        paymentsDataSourceImpl: PaymentsDataSourceImpl
    ): PaymentsDataSource = paymentsDataSourceImpl

    @Provides
    fun providePaymentsApi(retrofit: Retrofit) =
        retrofit.create(PaymentsApi::class.java)
}