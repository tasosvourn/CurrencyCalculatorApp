package com.example.data.di

import com.example.data.networking.EndPointConfiguration
import com.example.data.networking.RestApiService
import com.example.data.networking.SessionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideEndpointConfiguration(): EndPointConfiguration = EndPointConfiguration()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideSessionInterceptor(): SessionInterceptor = SessionInterceptor()

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sessionInterceptor: SessionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SECS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(sessionInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        endPointConfiguration: EndPointConfiguration
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPointConfiguration.getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }




    companion object {
        //Default timeout of the requests in seconds
        private const val DEFAULT_TIMEOUT_SECS = 60L
    }
}