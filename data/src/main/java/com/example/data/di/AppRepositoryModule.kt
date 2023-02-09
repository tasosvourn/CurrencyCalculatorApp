package com.example.data.di

import com.example.data.networking.RestApiService
import com.example.data.repository.LatestRatesRepositoryImpl
import com.example.data.repository.SymbolsRepositoryImpl
import com.example.domain.repository.LatestRatesRepository
import com.example.domain.repository.SymbolsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppRepositoryModule {

    @Provides
    fun provideSymbolsRepository(
        restApiService: RestApiService,
    ): SymbolsRepository = SymbolsRepositoryImpl(restApiService)

    @Provides
    fun provideLatestRatesRepository(
        restApiService: RestApiService,
    ): LatestRatesRepository = LatestRatesRepositoryImpl(restApiService)
}