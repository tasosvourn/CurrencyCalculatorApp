package com.example.currencycalculatorapp.utilities

object ConverterUtils {

    fun convertFromEuroBase(amountFrom: String, rateFrom: Double?, rateTo: Double?): String {
        return try {
            (amountFrom.toDouble() / rateFrom!! * rateTo!!).toString()
        } catch (_ : Exception) {
            ""
        }
    }
}