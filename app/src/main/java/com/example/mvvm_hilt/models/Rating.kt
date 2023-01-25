package com.example.mvvm_hilt.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.mvvm_hilt.models.Product

@Entity(foreignKeys = [ForeignKey(
    entity = Product::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("prodId"),
    onDelete = CASCADE
)]
)
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val prodId: Int,
    val count: Int,
    val rate: Double
)