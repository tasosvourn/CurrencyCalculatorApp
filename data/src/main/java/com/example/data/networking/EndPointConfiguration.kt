package com.example.data.networking

/** Configuration for the endpoint */
class EndPointConfiguration {
    private var baseUrl = "https://api.apilayer.com/exchangerates_data/"

    fun getBaseUrl(): String = baseUrl

    fun setBaseUrl(url: String) {
        baseUrl = url
    }

}