package com.example.data.networking

import com.example.data.model.response.BaseResponse
import com.example.domain.model.DomainResult
import com.example.domain.model.ErrorDomainModel
import com.example.domain.model.HttpErrorType
import com.example.domain.model.NetworkExceptionType
import retrofit2.Response

/** Handle API responses. Wrapper to every api call.
 *  @param K BaseResponse
 * @param T Generic Response
 * @param requestFunction
 * @return DomainResult
 */
suspend fun <K : BaseResponse, T : Response<K>> handleApiResponse(requestFunction: suspend () -> T): DomainResult<K> {
    try {
        val response = requestFunction.invoke()

        return if (response.isSuccessful) DomainResult.Success(response.body()!!)
        else DomainResult.Error(
            ErrorDomainModel(
                HttpErrorType(
                    response.code(),
                    response.message()
                )
            )
        )
    } catch (exception: Exception) {
        val message = exception.message ?: ""
        return DomainResult.Error(
            ErrorDomainModel(
                NetworkExceptionType(
                    exception = exception,
                    errorMessage = message.ifEmpty { exception.javaClass.name })
            )
        )
    }
}