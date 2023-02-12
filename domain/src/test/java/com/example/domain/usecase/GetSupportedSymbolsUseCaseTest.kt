package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptySupportedSymbolsErrorType
import com.example.domain.model.SupportedSymbolsResponseErrorType
import com.example.domain.model.symbols.SupportedSymbolsDomainModel
import com.example.domain.repository.SymbolsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import java.util.*
import kotlin.collections.HashMap

class GetSupportedSymbolsUseCaseTest {
    private val symbolsRepository: SymbolsRepository = mock()
    private val symbolsUseCase = GetSupportedSymbolsUseCase(symbolsRepository)

    @Before
    fun setUp() {
    }

    @Test
    fun getSymbols_emptyData() = runBlocking {
        symbolsRepository.stub {
            on { getSupportedSymbols() } doReturn flow {
                emit(DomainResult.Success(SupportedSymbolsDomainModel(
                    success = false,
                    symbols = sortedMapOf()
                )))
            }
        }

        val result = symbolsUseCase().first()
        assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        assertTrue(result.errorDomainModel.errorType is SupportedSymbolsResponseErrorType)
    }

    @Test
    fun getSymbols_Unsuccessful() = runBlocking {
        symbolsRepository.stub {
            on { getSupportedSymbols() } doReturn flow {
                emit(DomainResult.Success(SupportedSymbolsDomainModel(
                    success = false,
                    symbols = getExampleSortedMap()
                )))
            }
        }

        val result = symbolsUseCase().first()
        assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        assertTrue(result.errorDomainModel.errorType is SupportedSymbolsResponseErrorType)
    }

    @Test
    fun getSymbols_emptySupportedSymbols() = runBlocking {
        symbolsRepository.stub {
            on { getSupportedSymbols() } doReturn flow {
                emit(DomainResult.Success(SupportedSymbolsDomainModel(
                    success = true,
                    symbols = sortedMapOf()
                )))
            }
        }

        val result = symbolsUseCase().first()
        assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        assertTrue(result.errorDomainModel.errorType is EmptySupportedSymbolsErrorType)
    }

    @Test
    fun getSymbols_Successful() = runBlocking {
        symbolsRepository.stub {
            on { getSupportedSymbols() } doReturn flow {
                emit(DomainResult.Success(SupportedSymbolsDomainModel(
                    success = true,
                    symbols = sortedMapOf()
                )))
            }
        }

        val result = symbolsUseCase().first()
        assertTrue(result is DomainResult.Success)
        result as DomainResult.Success
        assertEquals("United Arab Emirates Dirham", result.data.symbols["AED"])
        assertEquals("Afghan Afghani", result.data.symbols["AFN"])
        assertEquals("Albanian Lek", result.data.symbols["ALL"])
        assertEquals("Armenian Dram", result.data.symbols["AMD"])
    }

    private fun getExampleSortedMap(): SortedMap<String, String> {
        val map = HashMap<String, String>()
        map["AED"] = "United Arab Emirates Dirham"
        map["AFN"] = "Afghan Afghani"
        map["ALL"] = "Albanian Lek"
        map["AMD"] = "Armenian Dram"
        return map.toSortedMap()
    }
}