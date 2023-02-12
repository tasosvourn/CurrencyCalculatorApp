package com.example.domain.usecase

import com.example.domain.model.DomainResult
import com.example.domain.model.EmptyLatestRatesErrorType
import com.example.domain.model.LatestRatesResponseErrorType
import com.example.domain.model.latestrates.LatestRatesDomainModel
import com.example.domain.repository.LatestRatesRepository
import com.example.domain.utilities.DateUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import kotlin.collections.HashMap


class GetLatestRatesUseCaseTest {
    private val latestRatesRepository: LatestRatesRepository = mock()
    private val latestRatesUseCase = GetLatestRatesUseCase(latestRatesRepository)

    @Before
    fun setUp() {
    }

    @Test
    fun getAllRates_emptyData() = runBlocking {
        latestRatesRepository.stub {
            on { getAllLatestRates(BASE) } doReturn flow {
                emit(DomainResult.Success(LatestRatesDomainModel(
                    success = false,
                    timestamp = null,
                    base = BASE,
                    date = "",
                    rates = hashMapOf()
                )))
            }
        }

        val result = latestRatesUseCase(BASE).first()
        Assert.assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        Assert.assertTrue(result.errorDomainModel.errorType is LatestRatesResponseErrorType)
    }

    @Test
    fun getAllRates_Unsuccessful() = runBlocking {
        latestRatesRepository.stub {
            on { getAllLatestRates(BASE) } doReturn flow {
                emit(DomainResult.Success(LatestRatesDomainModel(
                    success = false,
                    timestamp = TIMESTAMP,
                    base = BASE,
                    date = DATE,
                    rates = getExampleHashMap()
                )))
            }
        }

        val result = latestRatesUseCase(BASE).first()
        Assert.assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        Assert.assertTrue(result.errorDomainModel.errorType is LatestRatesResponseErrorType)
    }

    @Test
    fun getAllRates_emptyLatestRates() = runBlocking {
        latestRatesRepository.stub {
            on { getAllLatestRates(BASE) } doReturn flow {
                emit(DomainResult.Success(LatestRatesDomainModel(
                    success = true,
                    timestamp = TIMESTAMP,
                    base = BASE,
                    date = DATE,
                    rates = hashMapOf()
                )))
            }
        }

        val result = latestRatesUseCase(BASE).first()
        Assert.assertTrue(result is DomainResult.Error)
        result as DomainResult.Error
        Assert.assertTrue(result.errorDomainModel.errorType is EmptyLatestRatesErrorType)
    }

    @Test
    fun getAllRates_Successful() = runBlocking {
        latestRatesRepository.stub {
            on { getAllLatestRates(BASE) } doReturn flow {
                emit(DomainResult.Success(LatestRatesDomainModel(
                    success = true,
                    timestamp = TIMESTAMP,
                    base = BASE,
                    date = DATE,
                    rates = getExampleHashMap()
                )))
            }
        }

        val result = latestRatesUseCase(BASE).first()
        Assert.assertTrue(result is DomainResult.Success)
        result as DomainResult.Success
        Assert.assertEquals(0.72007, result.data.rates["GBP"])
        Assert.assertEquals(107.346001, result.data.rates["JPY"])
        Assert.assertEquals(0.813399, result.data.rates["EUR"])
    }

    private fun getExampleHashMap(): HashMap<String, Double> {
        val map = HashMap<String, Double>()
        map["GBP"] = 0.72007
        map["JPY"] = 107.346001
        map["EUR"] = 0.813399
        return map
    }
    
    companion object {
        private const val BASE = "USD"
        private const val DATE = "2021-03-17"
        private val TIMESTAMP = DateUtils.getStringDate(1519296206)
    }
}