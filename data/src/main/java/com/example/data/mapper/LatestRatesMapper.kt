package com.example.data.mapper

import com.example.data.model.response.latestrates.LatestRatesResponse
import com.example.domain.model.latestrates.LatestRatesDomainModel
import com.example.domain.utilities.DateUtils

fun LatestRatesResponse.mapToDomain(): LatestRatesDomainModel =
    LatestRatesDomainModel(
        success = success ?: false,
        timestamp = DateUtils.getStringDate(timestamp),
        base = base ?: "",
        date = date ?: "",
        rates = rates ?: hashMapOf()
    )