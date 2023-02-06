package com.example.domain.repository

import com.example.domain.model.DomainResult
import com.example.domain.model.symbols.SupportedSymbolsDomainModel
import kotlinx.coroutines.flow.Flow

interface SymbolsRepository {
    fun getSupportedSymbols(): Flow<DomainResult<SupportedSymbolsDomainModel>>
}