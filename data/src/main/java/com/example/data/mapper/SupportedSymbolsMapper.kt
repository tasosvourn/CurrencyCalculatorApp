package com.example.data.mapper

import com.example.data.model.response.symbols.SupportedSymbolsResponse
import com.example.domain.model.symbols.SupportedSymbolsDomainModel

fun SupportedSymbolsResponse.mapToDomain(): SupportedSymbolsDomainModel =
    SupportedSymbolsDomainModel(
        success = success ?: false,
        symbols = symbols.toSortedMap()
    )