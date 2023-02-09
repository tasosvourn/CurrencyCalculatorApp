package com.example.domain.model.latestrates

data class LatestRatesDomainModel(
    var success: Boolean,
    var timestamp: String?,
    var base: String,
    var date: String,
    var rates: HashMap<String, Double>,
)