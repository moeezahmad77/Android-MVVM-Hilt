package com.example.mvvm_hilt.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_hilt.db.FakerDB
import com.example.mvvm_hilt.models.Product
import com.example.mvvm_hilt.models.Rating
import com.example.mvvm_hilt.retrofit.FakerApi
import com.example.mvvm_hilt.utils.NetworkResponse
import javax.inject.Inject

class ProductRepository @Inject constructor(private val fakerService: FakerApi,
                                            private val fakerDb: FakerDB) {
    private val _products = MutableLiveData<NetworkResponse<List<Product>>>()
    val products: LiveData<NetworkResponse<List<Product>>>
        get() {
            return _products
        }


    suspend fun getProducts() {
        _products.postValue(NetworkResponse.Loading())
        try {
            val result = fakerService.getProducts()
            if (result.isSuccessful && result.body() != null) {
                if (result.code() == 200) {
                    fakerDb.getProductDAO().addProducts(result.body()!!)
                    result.body()!!.map {
                        val rating = Rating(0, it.id, it.rating!!.count, it.rating!!.rate)
                        fakerDb.getProductDAO().addProductRating(rating)
                    }
                    _products.postValue(NetworkResponse.Success(result.body()))
                } else {
                    _products.postValue(NetworkResponse.Failure(result.message()))
                }
            }
        } catch (e: Exception) {
            _products.postValue(NetworkResponse.Failure(e.message.toString()))
        }
    }
}