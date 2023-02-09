package com.example.domain.repository

import com.example.domain.model.DomainResult
import com.example.domain.model.latestrates.LatestRatesDomainModel
import kotlinx.coroutines.flow.Flow

/** Repository for the latest (rates) endpoint*/
interface LatestRatesRepository {
    fun getAllLatestRates(base: String): Flow<DomainResult<LatestRatesDomainModel>>
}