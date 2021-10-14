package com.chertilov.matching

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MatchingInteractor @Inject constructor(
    private val usersStorage: UsersStorage,
    private val sessionPreferences: SessionPreferences
) {

    fun getAllUsers(): Flow<Response<List<User>>> = usersStorage.getUsers()
        .map { it.filter { it.phoneNumber != sessionPreferences.getPhoneNumber() } }
        .map { Response.Success(it) }

    suspend fun addToMatches(matchedUser: User): Flow<User?> = usersStorage.getUser(sessionPreferences.getPhoneNumber())
        .map {
            val matches = it.matches.toMutableSet()
            if(matches.add(matchedUser.phoneNumber)) {
                usersStorage.addMatch(it.copy(matches = matches))
                matchedUser
            } else {
                null
            }
        }

}