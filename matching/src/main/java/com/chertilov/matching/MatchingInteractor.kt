package com.chertilov.matching

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.base.Response
import com.chertilov.core_api.database.MatchesStorage
import com.chertilov.core_api.database.UsersStorage
import com.chertilov.core_api.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchingInteractor @Inject constructor(
    private val usersStorage: UsersStorage,
    private val matchesStorage: MatchesStorage,
    private val sessionPreferences: SessionPreferences
) {

    fun getAllUsers(): Flow<Response<List<User>>> = usersStorage.getUsers()
        .map { it.filter { it.phoneNumber != sessionPreferences.getPhoneNumber() } }
        .map { Response.Success(it) }

    fun getRecentlyMatchedUser(phoneNumber: String): Flow<User> = usersStorage.getUser(phoneNumber)

    suspend fun addToMatches(matchedUser: User): Flow<User> = usersStorage.getUser(sessionPreferences.getPhoneNumber())
        .transform {
            val matches = it.matches.toMutableSet()
            if (matches.add(matchedUser.phoneNumber)) {
                usersStorage.replaceUserData(it.copy(matches = matches))
            }
            if (matchedUser.matches.contains(it.phoneNumber)) {
                matchesStorage.saveMatch(it.phoneNumber, matchedUser.phoneNumber)
                emit(matchedUser)
            }
        }
}
