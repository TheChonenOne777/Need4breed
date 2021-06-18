package com.chertilov.database

import com.chertilov.core_api.dto.Dog
import com.chertilov.database.entities.StorageDog

fun Dog.toStorage(): StorageDog = StorageDog(0, image)
fun StorageDog.toEntity(): Dog = Dog(image)