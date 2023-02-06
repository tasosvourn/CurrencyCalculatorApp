package com.example.data.repository

import com.example.data.mapper.mapToDomain
import com.example.data.networking.RestApiService
import com.example.data.networking.handleApiResponse
import com.example.domain.model.DomainResult
import com.example.domain.model.symbols.SupportedSymbolsDomainModel
import com.example.domain.repository.SymbolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SymbolsRepositoryImpl(
    private val restApiService: RestApiService
) : SymbolsRepository {

    override fun getSupportedSymbols(): Flow<DomainResult<SupportedSymbolsDomainModel>> = flow {
        when (val response = handleApiResponse { restApiService.getSupportedSymbols() }) {
            is DomainResult.Error -> emit(response)
            is DomainResult.Success -> {
                emit(DomainResult.Success(response.data.mapToDomain()))
            }
        }
    }


}