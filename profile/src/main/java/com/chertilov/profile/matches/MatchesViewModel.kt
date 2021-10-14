package com.chertilov.profile.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MatchesViewModel @Inject constructor(matchesInteractor: MatchesInteractor) : ViewModel() {

    val matches: LiveData<List<User>> = matchesInteractor.getMatches().flowOn(Dispatchers.IO).asLiveData()
}