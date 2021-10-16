package com.chertilov.profile.matches

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.database.MatchesStorage
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MatchesInteractor @Inject constructor(
    private val usersStorage: UsersStorage,
    private val matchesStorage: MatchesStorage,
    private val sessionPreferences: SessionPreferences
) {

    fun getMatches(): Flow<List<User>> = matchesStorage.getMatches(sessionPreferences.getPhoneNumber())
        .map { it.map { usersStorage.getUser(it).first() } }
}