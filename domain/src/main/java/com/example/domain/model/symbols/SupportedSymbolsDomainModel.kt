package com.example.domain.model.symbols

import java.util.*

data class SupportedSymbolsDomainModel(
    val success: Boolean,
    val symbols: SortedMap<String, String>
)