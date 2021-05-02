package com.gmail.yuliakazachok.corebanking.shared.tariffs.di

import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.api.TariffsApi
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.datasource.TariffsDataSource
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.datasource.TariffsDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.tariffs.data.repository.TariffsRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.repository.TariffsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object TariffsModule {

    @Provides
    fun provideTariffsRepository(
        tariffsRepositoryImpl: TariffsRepositoryImpl
    ): TariffsRepository = tariffsRepositoryImpl

    @Provides
    fun provideTariffsDataSource(
        tariffsDataSourceImpl: TariffsDataSourceImpl
    ): TariffsDataSource = tariffsDataSourceImpl

    @Provides
    fun provideTariffsApi(retrofit: Retrofit) =
        retrofit.create(TariffsApi::class.java)
}