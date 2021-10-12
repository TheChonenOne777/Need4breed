package com.chertilov.profile

import androidx.lifecycle.*
import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val interactor: ProfileInteractor): ViewModel() {

    val user: LiveData<User> = interactor.getCurrentUser().flowOn(Dispatchers.IO).asLiveData()

}