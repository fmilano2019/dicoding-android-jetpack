package com.example.moviecatalog.di

import com.example.moviecatalog.BuildConfig
import com.example.moviecatalog.data.api.ApiService
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }
    single { RemoteDataSource(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}