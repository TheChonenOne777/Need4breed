package com.chertilov.need4breed.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {

    @Query("SELECT * from dogs")
    fun getAll(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dogs: List<String>)

    @Query("DELETE from dogs")
    fun deleteAll()
}