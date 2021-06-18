package com.chertilov.need4breed.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chertilov.core_api.dto.Dog
import com.chertilov.need4breed.dogs.interactor.GetDogsUseCase
import com.chertilov.need4breed.dogs.interactor.RequestDogsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DogsViewModel(
    getDogsUseCase: GetDogsUseCase,
    private val requestDogsUseCase: RequestDogsUseCase
) : ViewModel() {

    val dogs: LiveData<List<Dog>> = getDogsUseCase().flowOn(Dispatchers.IO).asLiveData()

    fun onActivityCreated() {
        viewModelScope.launch { requestDogsUseCase() }
    }

}