package com.chertilov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chertilov.core_api.dto.Dog
import com.chertilov.database.entities.StorageDog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {

    @Query("SELECT * from dogs")
    fun getAll(): Flow<List<StorageDog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dogs: List<StorageDog>)

    @Query("DELETE from dogs")
    fun deleteAll()
}