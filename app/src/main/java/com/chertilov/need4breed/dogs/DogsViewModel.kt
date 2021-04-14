package com.chertilov.need4breed.dogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chertilov.need4breed.dogs.interactor.RequestDogsUseCase
import kotlinx.coroutines.launch

class DogsViewModel(private val requestDogsUseCase: RequestDogsUseCase) : ViewModel() {

    private val _dogs = MutableLiveData<List<String>>()
    val dogs = _dogs

    //    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onActivityCreated() {
        viewModelScope.launch {
            dogs.value = requestDogsUseCase()
        }
    }

}