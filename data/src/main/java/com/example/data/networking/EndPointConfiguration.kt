package com.example.data.networking

class EndPointConfiguration {
    private var baseUrl = "https://data.fixer.io/api/"

    fun getBaseUrl(): String = baseUrl

    fun setBaseUrl(url: String) {
        baseUrl = url
    }

}