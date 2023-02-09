package com.example.data.repository

import com.example.data.mapper.mapToDomain
import com.example.data.networking.RestApiService
import com.example.data.networking.handleApiResponse
import com.example.domain.model.DomainResult
import com.example.domain.model.latestrates.LatestRatesDomainModel
import com.example.domain.repository.LatestRatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LatestRatesRepositoryImpl(private val restApiService: RestApiService) :
    LatestRatesRepository {

    override fun getAllLatestRates(base: String): Flow<DomainResult<LatestRatesDomainModel>> = flow {
        when (val response = handleApiResponse { restApiService.getLatestRates(base = base) }) {
            is DomainResult.Error -> emit(response)
            is DomainResult.Success -> {
                emit(DomainResult.Success(response.data.mapToDomain()))
            }
        }
    }

}