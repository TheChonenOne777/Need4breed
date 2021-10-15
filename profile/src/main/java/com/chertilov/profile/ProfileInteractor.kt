package com.chertilov.profile

import com.chertilov.base_auth.SessionPreferences
import com.chertilov.core_api.database.UsersStorage
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val usersStorage: UsersStorage
) {

    fun getCurrentUser() = usersStorage.getUser(sessionPreferences.getPhoneNumber())

    suspend fun saveName(name: String) = getCurrentUser()
        .collect { usersStorage.replaceUserData(it.copy(nickname = name)) }

    suspend fun saveImage(imageUrl: String) = getCurrentUser()
        .collect { usersStorage.replaceUserData(it.copy(image = imageUrl)) }

    suspend fun saveBreed(breed: String) = getCurrentUser()
        .collect { usersStorage.replaceUserData(it.copy(breed = breed)) }

    suspend fun saveDescription(description: String) = getCurrentUser()
        .collect { usersStorage.replaceUserData(it.copy(description = description)) }

    fun logout() {
        sessionPreferences.clear()
    }
}