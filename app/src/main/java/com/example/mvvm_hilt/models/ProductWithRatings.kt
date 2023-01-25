package com.example.mvvm_hilt.models

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithRatings(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "prodId"
    )
    val rating: Rating
)
