package com.example.currencycalculatorapp.features

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.viewModelScope
import com.example.currencycalculatorapp.common.BaseViewModel
import com.example.currencycalculatorapp.utilities.ConverterUtils
import com.example.domain.model.DomainResult
import com.example.domain.usecase.GetLatestRatesUseCase
import com.example.domain.usecase.GetSupportedSymbolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import javax.inject.Inject

@HiltViewModel
class CalculatorCurrencyConverterViewModel @Inject constructor(
    private val getSupportedSymbolsUseCase: GetSupportedSymbolsUseCase,
    private val getLatestRatesUseCase: GetLatestRatesUseCase,
) : BaseViewModel() {

    private val _expression = MutableStateFlow("")
    val expression = _expression.asStateFlow()

    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()

    private val _conversionResult = MutableStateFlow("")
    val conversionResult = _conversionResult.asStateFlow()

    private val _supportedSymbols = MutableStateFlow(emptyList<String>())
    val supportedSymbols = _supportedSymbols.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    private var currentSymbol = ""
    private var rateFrom: Double? = null
    private var rateTo: Double? = null
    private var rates = hashMapOf<String, Double>()

    init {
        getSupportedSymbols()
        getLatestRates()
        clearAll()
        Log.d("VIEW_MODEL", "init worked fine ")
    }

    private fun getSupportedSymbols() {
        viewModelScope.launch {
            when (val response = getSupportedSymbolsUseCase().first()) {
                is DomainResult.Error -> _error.value = response.errorDomainModel.errorMessage ?: ""
                is DomainResult.Success -> {
                    Log.d("SYMBOLS_TAG", "call worked fine ")
                    if (response.data.symbols.isNotEmpty()) {
                        val defaultSymbol = response.data.symbols.filter { it.key == EUR }
                        currentSymbol =
                            defaultSymbol.keys.first() + " - " + defaultSymbol.values.first()
                        _supportedSymbols.value =
                            response.data.symbols.map { it.key + " - " + it.value }
                    }
                }
                else -> Unit
            }
        }
    }

    private fun getLatestRates() {
        viewModelScope.launch {
            when (val response = getLatestRatesUseCase(currentSymbol).first()) {
                is DomainResult.Error -> _error.value = response.errorDomainModel.errorMessage ?: ""
                is DomainResult.Success -> {
                    Log.d("RATES_TAG", "call worked fine")
                    if (response.data.rates.isNotEmpty()) {
                        rates = response.data.rates
                        rateFrom = rates[EUR]
                    }
                }
                else -> Unit
            }
        }
    }

    fun getCurrentSymbol(): String {
        return currentSymbol
    }

    fun calculateTheExpression(data: String, clearData: Boolean) {
        if (_result.value.isNotEmpty()) {
            _expression.value = ""
        }
        if (clearData) {
            _result.value = ""
            _conversionResult.value = ""
            _expression.value += data
        } else {
            _expression.value += _result.value
            _expression.value += data
            _conversionResult.value = ""
            _result.value = ""
        }
    }

    fun clearAll() {
        _expression.value = ""
        _result.value = ""
        _conversionResult.value = ""
    }

    fun deleteLastDigit() {
        val currentExpression = _expression.value
        if (currentExpression.isNotBlank()) {
            _expression.value = currentExpression.substring(0, currentExpression.length - 1)
        }
        _result.value = ""
        _conversionResult.value = ""
    }

    fun setResultOnExpression() {
        try {
            val expectedExpression = ExpressionBuilder(_expression.value).build()
            val expectedResult = expectedExpression.evaluate()
            val longResult = expectedResult.toLong()
            if (expectedResult == longResult.toDouble()) {
                _result.value = longResult.toString()
                if (rateFrom != rateTo) _conversionResult.value =
                    ConverterUtils.convertFromEuroBase(longResult.toString(), rateFrom, rateTo)
            } else {
                _result.value = expectedResult.toString()
                if (rateFrom != rateTo) _conversionResult.value =
                    ConverterUtils.convertFromEuroBase(expectedResult.toString(), rateFrom, rateTo)
            }
        } catch (_: Exception) {
        }
    }

    val spinnerFromListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            rateFrom = rates[_supportedSymbols.value[position].substring(0, 3)]
            _conversionResult.value = ConverterUtils.convertFromEuroBase(_result.value, rateFrom, rateTo)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            /* Not used */
        }

    }

    val spinnerToListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            rateTo = rates[_supportedSymbols.value[position].substring(0, 3)]
            _conversionResult.value = ConverterUtils.convertFromEuroBase(_result.value, rateFrom, rateTo)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            /* Not used */
        }

    }

    companion object {
        const val EUR = "EUR"
    }
}
