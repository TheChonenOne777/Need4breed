package com.chertilov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}