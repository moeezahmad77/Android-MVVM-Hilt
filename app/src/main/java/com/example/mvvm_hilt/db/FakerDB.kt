package com.example.mvvm_hilt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_hilt.models.Product
import com.example.mvvm_hilt.models.Rating

@Database(entities = [Product::class, Rating::class], version = 1)
abstract class FakerDB: RoomDatabase() {
    abstract fun getProductDAO(): ProductDAO
}