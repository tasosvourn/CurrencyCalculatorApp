package com.example.currencycalculatorapp.features

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.currencycalculatorapp.R
import com.example.currencycalculatorapp.common.BaseActivity
import com.example.currencycalculatorapp.databinding.ActivityCalculatorCurrencyConverterBinding
import com.example.currencycalculatorapp.utilities.collect
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorCurrencyConverterActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityCalculatorCurrencyConverterBinding

    private val viewModel: CalculatorCurrencyConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCalculatorCurrencyConverterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel.expression.collect(this) {
            viewBinding.expression.text = it
        }

        viewModel.result.collect(this) {
            viewBinding.result.text = it
        }

        viewModel.conversionResult.collect(this) {
            viewBinding.conversionResult.text = it
        }

        viewModel.error.collect(this) {
            onError(it)
        }

        viewModel.supportedSymbols.collect(this) {
            val spinnerAdapter = ArrayAdapter(this,
                R.layout.spinner_row_layout, it.toTypedArray())
            spinnerAdapter.setDropDownViewResource(R.layout.spinner_row_layout)
            with(viewBinding.spinnerFrom) {
                adapter = spinnerAdapter
                setSelection(it.indexOf(viewModel.getCurrentSymbol()))
                prompt = getString(R.string.spinner_title)
                onItemSelectedListener = viewModel.spinnerFromListener
            }
            with(viewBinding.spinnerTo) {
                adapter = spinnerAdapter
                setSelection(it.indexOf(viewModel.getCurrentSymbol()))
                prompt = getString(R.string.spinner_title)
                onItemSelectedListener = viewModel.spinnerToListener
            }
        }

        setUpNumbersListeners()
        setUpOperatorsListeners()
        setAllCleanListener()
        setDeleteListener()
        setExpectedResult()
    }

    private fun setExpectedResult() {
        viewBinding.buttonEquals.setOnClickListener { viewModel.setResultOnExpression() }
    }

    private fun setDeleteListener() {
        viewBinding.buttonDelete.setOnClickListener { viewModel.deleteLastDigit() }
    }

    private fun setAllCleanListener() {
        viewBinding.buttonAllClear.setOnClickListener { viewModel.clearAll() }
    }

    private fun setUpOperatorsListeners() {
        viewBinding.apply {
            buttonPlus.setOnClickListener { viewModel.calculateTheExpression("+", true) }
            buttonMinus.setOnClickListener { viewModel.calculateTheExpression("-", true) }
            buttonTimes.setOnClickListener { viewModel.calculateTheExpression("*", true) }
            buttonDivisor.setOnClickListener { viewModel.calculateTheExpression("/", true) }
        }
    }

    private fun setUpNumbersListeners() {
        viewBinding.apply {
            button0.setOnClickListener { viewModel.calculateTheExpression("0", true) }
            button1.setOnClickListener { viewModel.calculateTheExpression("1", true) }
            button2.setOnClickListener { viewModel.calculateTheExpression("2", true) }
            button3.setOnClickListener { viewModel.calculateTheExpression("3", true) }
            button4.setOnClickListener { viewModel.calculateTheExpression("4", true) }
            button5.setOnClickListener { viewModel.calculateTheExpression("5", true) }
            button6.setOnClickListener { viewModel.calculateTheExpression("6", true) }
            button7.setOnClickListener { viewModel.calculateTheExpression("7", true) }
            button8.setOnClickListener { viewModel.calculateTheExpression("8", true) }
            button9.setOnClickListener { viewModel.calculateTheExpression("9", true) }
            buttonDecimalPoint.setOnClickListener { viewModel.calculateTheExpression(".", true) }
        }
    }

    private fun onError(errorMessage: String) {
        if (errorMessage.isNotBlank()) {
            Snackbar.make(viewBinding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }
}