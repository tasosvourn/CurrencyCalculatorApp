package com.example.data.networking

import com.example.data.model.response.latestrates.LatestRatesResponse
import com.example.data.model.response.symbols.SupportedSymbolsResponse
import com.example.data.networking.Headers.API_KEY
import com.example.data.networking.Urls.LATEST
import com.example.data.networking.Urls.SYMBOLS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApiService {

    @GET(SYMBOLS)
    suspend fun getSupportedSymbols(
        @Query("apikey") api_key: String = API_KEY
    ): Response<SupportedSymbolsResponse>

    @GET(LATEST)
    suspend fun getLatestRates(
        @Query("apikey") api_key: String = API_KEY,
        @Query("base") base: String
    ): Response<LatestRatesResponse>
}
