package com.chertilov.matching

import androidx.lifecycle.*
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchingViewModel @Inject constructor(private val interactor: MatchingInteractor) : ViewModel() {

    private val _users = MutableLiveData<Response<List<User>>>()
    val users: LiveData<Response<List<User>>> = _users

    private val _matchedUser = MutableLiveData<User>()
    val matchedUser: LiveData<User> = _matchedUser

    init {
        _users.value = Response.Loading
        viewModelScope.launch {
            delay(2000)
            getUsers()
        }
    }

    fun onMatchClicked(user: User) {
        viewModelScope.launch {
            interactor.addToMatches(user).flowOn(Dispatchers.IO).collect { if (it != null) _matchedUser.value = it }
        }
    }

    fun onRefresh(){
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            interactor.getAllUsers().flowOn(Dispatchers.IO).collect { _users.value = it }
        }
    }
}