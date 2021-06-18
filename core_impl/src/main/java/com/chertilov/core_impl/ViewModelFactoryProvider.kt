package com.chertilov.core_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class ViewModelFactoryProvider @Inject constructor(
    private val creators: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creators.entries.find { modelClass.isAssignableFrom(it.key) }?.value as T
            ?: throw ClassNotFoundException("No model provided for ${modelClass.simpleName}")
    }
}