package com.chertilov.database.entities

import androidx.room.Entity

@Entity(tableName = "matches", primaryKeys = ["phone_number_in", "phone_number_out"])
data class StorageMatch(
    val phone_number_in: String,
    val phone_number_out: String
)