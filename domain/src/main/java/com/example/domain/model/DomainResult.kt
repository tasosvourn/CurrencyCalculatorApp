package com.example.domain.model

sealed class DomainResult<out T: Any> {
    data class Success<out T: Any>(val data: T) : DomainResult<T>()
    data class Error(val errorDomainModel: ErrorDomainModel): DomainResult<Nothing>()
}