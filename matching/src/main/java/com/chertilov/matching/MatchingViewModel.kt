package com.chertilov.matching

import androidx.lifecycle.*
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchingViewModel @Inject constructor(private val interactor: MatchingInteractor) : ViewModel() {

    val users: LiveData<List<User>> = interactor.getAllUsers().flowOn(Dispatchers.IO).asLiveData()

    private val _matchedUser = MutableLiveData<User>()
    val matchedUser: LiveData<User> = _matchedUser

    fun onMatchClicked(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactor.addToMatches(user).collect { if (it != null) _matchedUser.value = it }
            }
        }
    }
}