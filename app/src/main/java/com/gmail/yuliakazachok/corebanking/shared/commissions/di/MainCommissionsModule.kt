package com.gmail.yuliakazachok.corebanking.shared.commissions.di

import com.gmail.yuliakazachok.corebanking.shared.commissions.data.api.CommissionsApi
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.datasource.CommissionsDataSource
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.datasource.CommissionsDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.commissions.data.repository.CommissionsRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.repository.CommissionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object MainCommissionsModule {

    @Provides
    fun bindMainCommissionsRepository(
        commissionsRepositoryImpl: CommissionsRepositoryImpl
    ): CommissionsRepository = commissionsRepositoryImpl

    @Provides
    fun bindMainCommissionsDataSource(
        mainCommissionsDataSourceImpl: CommissionsDataSourceImpl
    ): CommissionsDataSource = mainCommissionsDataSourceImpl

    @Provides
    fun provideMainCommissionsApi(retrofit: Retrofit) =
        retrofit.create(CommissionsApi::class.java)
}