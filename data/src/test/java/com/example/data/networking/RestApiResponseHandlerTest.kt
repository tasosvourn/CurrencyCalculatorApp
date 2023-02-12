package com.example.data.networking

import com.example.data.model.response.symbols.SupportedSymbolsResponse
import com.example.domain.model.DomainResult
import com.example.domain.model.HttpErrorType
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class RestApiResponseHandlerTest {

    private val mockSuccessResponse =
        "{" +
                "'success': true," +
                "'symbols': {" +
                "'AED': 'United Arab Emirates Dirham'," +
                "'AFN': 'Afghan Afghani'," +
                "'ALL': 'Albanian Lek'," +
                "'AMD': 'Armenian Dram'" +
                "}" +
                "}"

    private lateinit var supportedSymbolsSuccessResponse: SupportedSymbolsResponse

    @Before
    fun setUp() {
        supportedSymbolsSuccessResponse =
            Gson().fromJson(mockSuccessResponse, SupportedSymbolsResponse::class.java)
    }

    @Test
    fun testHandleApiResponse_ReturnsSuccess() = runBlocking {
        val expectedOutput = true
        val response = handleApiResponse { processResponse(supportedSymbolsSuccessResponse) }
        assertTrue(response is DomainResult.Success)
        response as DomainResult.Success
        assertEquals(expectedOutput, response.data.success)
    }

    @Test
    fun testHandleApiResponse_ReturnsHttpError() = runBlocking {
        val expectedOutput = 401
        val response = handleApiResponse { processHttpErrorResponse() }
        assertTrue(response is DomainResult.Error)
        assertTrue((response as DomainResult.Error).errorDomainModel.errorType is HttpErrorType)
        assertEquals(expectedOutput,
            (response.errorDomainModel.errorType as HttpErrorType).errorCode)

    }

    private fun processResponse(supportedSymbolsResponse: SupportedSymbolsResponse): Response<SupportedSymbolsResponse> {
        return Response.success(supportedSymbolsResponse)
    }

    private fun processHttpErrorResponse(): Response<SupportedSymbolsResponse> {
        return Response.error(HttpURLConnection.HTTP_UNAUTHORIZED,
            "HTTP_UNAUTHORIZED".toResponseBody("http://localhost".toMediaTypeOrNull()))
    }
}
