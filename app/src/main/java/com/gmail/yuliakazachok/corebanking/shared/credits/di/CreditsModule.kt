package com.gmail.yuliakazachok.corebanking.shared.credits.di

import com.gmail.yuliakazachok.corebanking.shared.credits.data.api.CreditsApi
import com.gmail.yuliakazachok.corebanking.shared.credits.data.datasource.CreditsDataSource
import com.gmail.yuliakazachok.corebanking.shared.credits.data.datasource.CreditsDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.credits.data.repository.CreditsRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.repository.CreditsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object CreditsModule {

    @Provides
    fun provideCreditsRepository(
        creditsRepositoryImpl: CreditsRepositoryImpl
    ): CreditsRepository = creditsRepositoryImpl

    @Provides
    fun provideCreditsDataSource(
        creditsDataSourceImpl: CreditsDataSourceImpl
    ): CreditsDataSource = creditsDataSourceImpl

    @Provides
    fun provideCreditsApi(retrofit: Retrofit) =
        retrofit.create(CreditsApi::class.java)
}