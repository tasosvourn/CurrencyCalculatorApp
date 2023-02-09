package com.example.data.model.response.latestrates

import com.example.data.model.response.BaseResponse
import com.google.gson.annotations.SerializedName

class LatestRatesResponse: BaseResponse() {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("timestamp")
    var timestamp: Long? = null

    @SerializedName("base")
    var base: String? = null

    @SerializedName("date")
    var date: String? = null

    @SerializedName("rates")
    var rates: HashMap<String, Double>? = null
}