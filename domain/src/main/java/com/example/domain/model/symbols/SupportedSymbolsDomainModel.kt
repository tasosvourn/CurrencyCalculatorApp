package com.example.domain.model.symbols

data class SupportedSymbolsDomainModel(
    val success: Boolean,
    val symbols: HashMap<String, String>
)