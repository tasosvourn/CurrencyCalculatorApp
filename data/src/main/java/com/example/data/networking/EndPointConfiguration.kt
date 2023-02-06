package com.example.data.networking

class EndPointConfiguration {
    private var baseUrl = "https://api.apilayer.com/fixer/"

    fun getBaseUrl(): String = baseUrl

    fun setBaseUrl(url: String) {
        baseUrl = url
    }

}