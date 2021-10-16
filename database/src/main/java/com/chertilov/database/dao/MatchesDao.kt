package com.chertilov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chertilov.database.entities.StorageDog
import com.chertilov.database.entities.StorageMatch
import com.chertilov.database.entities.StorageUser
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {

    @Query("SELECT * from matches WHERE phone_number_in=:phoneNumber")
    fun get(phoneNumber: String): Flow<List<StorageMatch>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(match: StorageMatch)
}