package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptyLatestRatesErrorType
import com.example.domain.model.ErrorDomainModel
import com.example.domain.model.LatestRatesResponseErrorType
import com.example.domain.model.latestrates.LatestRatesDomainModel
import com.example.domain.repository.LatestRatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLatestRatesUseCase @Inject constructor(private val latestRatesRepository: LatestRatesRepository) {
    operator fun invoke(base: String): Flow<DomainResult<LatestRatesDomainModel>> = flow {
        when (val result = latestRatesRepository.getAllLatestRates(base).first()) {
            is DomainResult.Error -> emit(result)
            is DomainResult.Success -> {
                if (!result.data.success) {
                    emit(DomainResult.Error(ErrorDomainModel(LatestRatesResponseErrorType)))
                }
                if (result.data.rates.isNotEmpty()) {
                    emit(result)
                } else {
                    emit(DomainResult.Error(ErrorDomainModel(EmptyLatestRatesErrorType)))
                }
            }
        }
    }

}