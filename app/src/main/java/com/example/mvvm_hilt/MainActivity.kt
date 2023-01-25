package com.example.mvvm_hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_hilt.utils.NetworkResponse
import com.example.mvvm_hilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        subscriber()
    }

    private fun subscriber() {
        mainViewModel.products.observe(this) {
            when(it) {
                is NetworkResponse.Failure -> {
                    Toast.makeText(this, "FAIL ${it.error.toString()}", Toast.LENGTH_LONG).show()
                }
                is NetworkResponse.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()
                }
                is NetworkResponse.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}