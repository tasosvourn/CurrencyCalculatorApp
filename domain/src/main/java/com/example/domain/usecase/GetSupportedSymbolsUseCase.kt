package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.symbols.SupportedSymbolsDomainModel
import com.example.domain.repository.SymbolsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSupportedSymbolsUseCase @Inject constructor(private val symbolsRepository: SymbolsRepository) {
    operator fun invoke(): Flow<DomainResult<SupportedSymbolsDomainModel>> = symbolsRepository.getSupportedSymbols()
}