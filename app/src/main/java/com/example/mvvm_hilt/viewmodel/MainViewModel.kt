package com.example.mvvm_hilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_hilt.models.Product
import com.example.mvvm_hilt.repository.ProductRepository
import com.example.mvvm_hilt.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    val products: LiveData<NetworkResponse<List<Product>>>
        get() {
            return productRepository.products
        }

    init {
        viewModelScope.launch {
            productRepository.getProducts()
        }
    }

}