package com.example.mvvm_hilt.retrofit

import com.example.mvvm_hilt.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface FakerApi {

    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>
}