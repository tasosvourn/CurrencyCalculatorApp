package com.example.domain.model

/**
 Base class for the domain results.
 A result could be either Success or Error.
 */
sealed class DomainResult<out T: Any> {
    data class Success<out T: Any>(val data: T) : DomainResult<T>()
    data class Error(val errorDomainModel: ErrorDomainModel): DomainResult<Nothing>()
}