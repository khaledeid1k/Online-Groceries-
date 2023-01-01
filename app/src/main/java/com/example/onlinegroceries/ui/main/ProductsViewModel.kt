package com.example.onlinegroceries.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.Network.Repository.MainRepository
import com.example.onlinegroceries.Network.data.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class ProductsViewModel
constructor(private val mainRepository: MainRepository)
    : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val productsList = MutableLiveData<ProductModel>()
    // if i want to cansel Coroutine
    var job: Job? = null


    fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO ).launch {
            val response = mainRepository.getProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    productsList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}