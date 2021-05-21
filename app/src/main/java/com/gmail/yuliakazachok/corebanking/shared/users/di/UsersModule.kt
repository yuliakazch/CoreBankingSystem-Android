package com.gmail.yuliakazachok.corebanking.shared.users.di

import com.gmail.yuliakazachok.corebanking.shared.users.data.api.UsersApi
import com.gmail.yuliakazachok.corebanking.shared.users.data.datasource.UsersDataSource
import com.gmail.yuliakazachok.corebanking.shared.users.data.datasource.UsersDataSourceImpl
import com.gmail.yuliakazachok.corebanking.shared.users.data.repository.UsersRepositoryImpl
import com.gmail.yuliakazachok.corebanking.shared.users.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class UsersModule {

    @Provides
    fun provideUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository = usersRepositoryImpl

    @Provides
    fun provideUsersDataSource(
        usersDataSourceImpl: UsersDataSourceImpl
    ): UsersDataSource = usersDataSourceImpl

    @Provides
    fun provideUsersApi(retrofit: Retrofit) =
        retrofit.create(UsersApi::class.java)
}