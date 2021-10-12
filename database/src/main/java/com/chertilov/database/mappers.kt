package com.chertilov.database

import com.chertilov.core_api.dto.Dog
import com.chertilov.core_api.dto.User
import com.chertilov.database.entities.StorageDog
import com.chertilov.database.entities.StorageUser

fun Dog.toStorage(): StorageDog = StorageDog(0, image)
fun StorageDog.toEntity(): Dog = Dog(image)

fun User.toStorage(): StorageUser = StorageUser(phoneNumber, image, nickname, description, breed)
fun StorageUser.toEntity(): User = User(phoneNumber, image, nickname, description, breed)