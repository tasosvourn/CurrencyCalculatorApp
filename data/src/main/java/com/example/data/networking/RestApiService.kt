package com.example.data.networking

import com.example.data.model.response.symbols.SupportedSymbolsResponse
import com.example.data.networking.Headers.API_KEY
import com.example.data.networking.Urls.SYMBOLS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface RestApiService {

    @GET(SYMBOLS)
    fun getSupportedSymbols(
        @Header("apikey") api_key: String = API_KEY
    ): Response<SupportedSymbolsResponse>

}
