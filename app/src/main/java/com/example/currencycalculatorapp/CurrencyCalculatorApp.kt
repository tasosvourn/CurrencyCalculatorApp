package com.example.currencycalculatorapp

import android.app.Application
import com.example.data.networking.EndPointConfiguration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CurrencyCalculatorApp : Application() {

    @Inject
    lateinit var endPointConfiguration: EndPointConfiguration
}