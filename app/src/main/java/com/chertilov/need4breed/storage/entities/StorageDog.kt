package com.chertilov.need4breed.storage.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "dogs", indices = [Index("id")])
data class StorageDog(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    val image: String,
)