package com.chertilov.database.dao

import androidx.room.*
import com.chertilov.database.entities.StorageUser
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * from users")
    fun getAll(): Flow<List<StorageUser>>

    @Query("SELECT * from users WHERE phoneNumber=:phoneNumber")
    fun get(phoneNumber: String): Flow<StorageUser>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: StorageUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateMatch(user: StorageUser)
}