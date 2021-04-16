package com.chertilov.need4breed.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chertilov.need4breed.dogs.DogsViewModel
import com.chertilov.need4breed.dogs.interactor.GetDogsUseCase
import com.chertilov.need4breed.dogs.interactor.RequestDogsUseCase

class ViewModelFactory(
        private val requestDogsUseCase: RequestDogsUseCase,
        private val getDogsUseCase: GetDogsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            DogsViewModel(getDogsUseCase, requestDogsUseCase) as T
}