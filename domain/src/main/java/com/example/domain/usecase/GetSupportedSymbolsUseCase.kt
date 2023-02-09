package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptySupportedSymbolsErrorType
import com.example.domain.model.SupportedSymbolsResponseErrorType
import com.example.domain.model.ErrorDomainModel
import com.example.domain.model.symbols.SupportedSymbolsDomainModel
import com.example.domain.repository.SymbolsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSupportedSymbolsUseCase @Inject constructor(private val symbolsRepository: SymbolsRepository) {
    operator fun invoke(): Flow<DomainResult<SupportedSymbolsDomainModel>> = flow {
        when (val result = symbolsRepository.getSupportedSymbols().first()) {
            is DomainResult.Error -> emit(result)
            is DomainResult.Success -> {
                if (!result.data.success) {
                    emit(DomainResult.Error(ErrorDomainModel(SupportedSymbolsResponseErrorType)))
                }
                if (result.data.symbols.isNotEmpty()) {
                    emit(result)
                } else {
                    emit(DomainResult.Error(ErrorDomainModel(EmptySupportedSymbolsErrorType)))
                }
            }
        }
    }


}