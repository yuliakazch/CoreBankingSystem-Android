package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.di

import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.api.MainCommissionsApi
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.datasource.MainCommissionsDataSource
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.datasource.MainCommissionsDataSourceImpl
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.data.repository.MainCommissionsRepositoryImpl
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.repository.MainCommissionsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object MainCommissionsModule {

    @Provides
    fun bindMainCommissionsRepository(
        mainCommissionsRepositoryImpl: MainCommissionsRepositoryImpl
    ): MainCommissionsRepository = mainCommissionsRepositoryImpl

    @Provides
    fun bindMainCommissionsDataSource(
        mainCommissionsDataSourceImpl: MainCommissionsDataSourceImpl
    ): MainCommissionsDataSource = mainCommissionsDataSourceImpl

    @Provides
    fun provideMainCommissionsApi(retrofit: Retrofit) =
        retrofit.create(MainCommissionsApi::class.java)
}