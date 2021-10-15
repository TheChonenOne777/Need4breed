package com.chertilov.matching

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class MatchingInteractor @Inject constructor(
    private val usersStorage: UsersStorage,
    private val sessionPreferences: SessionPreferences,
    private val stateHelper: StateHelper
) {

    fun getAllUsers(): Flow<Response<List<User>>> = usersStorage.getUsers()
        .map { it.filter { it.phoneNumber != sessionPreferences.getPhoneNumber() } }
        .map { Response.Success(it) }

    fun getRecentlyMatchedUser(): User? = stateHelper.matchedUser

    suspend fun addToMatches(matchedUser: User): Flow<User> = usersStorage.getUser(sessionPreferences.getPhoneNumber())
        .transform {
            val matches = it.matches.toMutableSet()
            if (matches.add(matchedUser.phoneNumber)) {
                usersStorage.replaceUserData(it.copy(matches = matches))
            }
            if (matchedUser.matches.contains(it.phoneNumber)) {
                stateHelper.matchedUser = matchedUser
                emit(matchedUser)
            }
        }
}
