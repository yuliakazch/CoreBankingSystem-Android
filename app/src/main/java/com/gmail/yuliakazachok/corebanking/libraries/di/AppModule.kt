package com.gmail.yuliakazachok.corebanking.libraries.di

import android.content.Context
import android.content.SharedPreferences
import com.gmail.yuliakazachok.corebanking.libraries.utils.Config
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle.TOKEN
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle.TOKEN_REFERENCES
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(TOKEN_REFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        sharedPreferences: SharedPreferences
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val headersInterceptor = Interceptor { chain ->
            val original = chain.request()
            val token = sharedPreferences.getString(TOKEN, null)
            val requestBuilder: Request.Builder = original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headersInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("dd-MM-yyyy").create()))
        .baseUrl(Config.BASE_URL)
        .client(okHttpClient)
        .build()
}