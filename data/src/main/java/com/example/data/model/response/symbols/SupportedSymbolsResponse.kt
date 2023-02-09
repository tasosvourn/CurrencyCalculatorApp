package com.example.data.model.response.symbols

import com.example.data.model.response.BaseResponse
import com.google.gson.annotations.SerializedName

class SupportedSymbolsResponse : BaseResponse() {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("symbols")
    var symbols: HashMap<String, String> = hashMapOf()
}