package com.gmail.yuliakazachok.corebanking.shared.clients.di

import com.gmail.yuliakazachok.corebanking.shared.clients.data.api.ClientsApi
import com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource.ClientsDataSource
import com.gmail.yuliakazachok.corebanking.shared.clients.data.datasource.ClientsDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.clients.data.repository.ClientsRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository.ClientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object ClientsModule {

    @Provides
    fun provideClientsRepository(
        clientsRepositoryImpl: ClientsRepositoryImpl
    ): ClientsRepository = clientsRepositoryImpl

    @Provides
    fun provideClientsDataSource(
        clientsDataSourceImpl: ClientsDataSourceImpl
    ): ClientsDataSource = clientsDataSourceImpl

    @Provides
    fun provideClientsApi(retrofit: Retrofit) =
        retrofit.create(ClientsApi::class.java)
}