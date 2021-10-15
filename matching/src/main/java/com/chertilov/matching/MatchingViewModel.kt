package com.chertilov.matching

import androidx.lifecycle.*
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MatchingViewModel @Inject constructor(private val interactor: MatchingInteractor) : ViewModel() {

    private val _users = MutableLiveData<Response<List<User>>>()
    val users: LiveData<Response<List<User>>> = _users

    private val _matchedUser = MutableLiveData<User>()
    val matchedUser: LiveData<User> = _matchedUser

    private var currentIndex = 0
    val swipeModel: LiveData<SwipeModel?> = _users.map {
        (it as? Response.Success)?.value?.let {
            SwipeModel(
                it[currentIndex % it.size],
                it[(currentIndex + 1) % it.size]
            )
        }
    }

    fun onMatchFragmentOpened() {
        _users.value = Response.Loading
        viewModelScope.launch {
            delay(2000)
            getUsers()
        }
    }

    fun onMatchCardsFragmentOpened() {
        _users.value = Response.Loading
        viewModelScope.launch { getUsers() }
    }

    fun onMatchDialogOpened(){
        _matchedUser.value = interactor.getRecentlyMatchedUser()
    }

    fun onMatchClicked(user: User) {
        viewModelScope.launch {
            interactor.addToMatches(user).flowOn(Dispatchers.IO).collect { it?.let { _matchedUser.value = it } }
        }
    }

    fun onRefresh() {
        getUsers()
    }

    fun onSwipe(sideId: Int) {
        if (sideId == R.id.offScreenLike) swipeModel.value?.top?.let { onMatchClicked(it) }
        currentIndex++
        _users.value = _users.value
    }

    private fun getUsers() {
        viewModelScope.launch {
            interactor.getAllUsers().flowOn(Dispatchers.IO).collect { _users.value = it }
        }
    }
}