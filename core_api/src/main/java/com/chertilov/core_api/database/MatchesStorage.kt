package com.chertilov.core_api.database

import kotlinx.coroutines.flow.Flow

interface MatchesStorage {

    fun getMatches(phoneNumber: String): Flow<List<String>>

    suspend fun saveMatch(phoneNumber: String, match: String)
}