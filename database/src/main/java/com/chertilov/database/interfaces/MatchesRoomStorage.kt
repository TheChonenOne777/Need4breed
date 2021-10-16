package com.chertilov.database.interfaces

import com.chertilov.core_api.database.MatchesStorage
import com.chertilov.database.dao.MatchesDao
import com.chertilov.database.entities.StorageMatch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchesRoomStorage @Inject constructor(
    private val matchesDao: MatchesDao,
    private val ioDispatcher: CoroutineDispatcher
) : MatchesStorage {

    override fun getMatches(phoneNumber: String): Flow<List<String>> = matchesDao.get(phoneNumber)
        .map { it.map { it.phone_number_out } }

    override suspend fun saveMatch(phoneNumber: String, match: String) = withContext(ioDispatcher) {
        matchesDao.insert(StorageMatch(phoneNumber, match))
        matchesDao.insert(StorageMatch(match, phoneNumber))
    }
}