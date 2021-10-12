package com.chertilov.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class StorageUser(
    @PrimaryKey
    val phoneNumber: String,
    val image: String,
    val nickname: String,
    val description: String,
    val breed: String
)