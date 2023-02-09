package com.example.currencycalculatorapp.common

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/** Base activity to reduce boilerplate code and share the common logic between all the activities. */
@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    private val viewModel: BaseViewModel by viewModels()
}