package com.example.currencycalculatorapp.utilities

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow

inline fun <T> Flow<T>.collect(
    owner: LifecycleOwner,
    crossinline onCollect: suspend (T) -> Unit
) = owner.lifecycleScope.launchWhenStarted {
    collect { onCollect(it) }
}