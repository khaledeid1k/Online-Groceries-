package com.example.onlinegroceries.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.Network.Repository.MainRepository
import com.example.onlinegroceries.Network.data.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
//@HiltViewModel
class ProductsViewModel
//@Inject
constructor(private val mainRepository: MainRepository)
    : ViewModel() {
    // Backing property
    private var _errorMessage = MutableLiveData<String>()
    private var _productsList = MutableLiveData<ProductModel>()

    val errorMessage : MutableLiveData<String> get() = _errorMessage
    val productsList : MutableLiveData<ProductModel> get() = _productsList
    // if i want to cansel Coroutine
    private var job: Job? = null


    fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO ).launch {
            val response = mainRepository.getProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _productsList.postValue(response.body())
                } else {
                    _errorMessage.postValue(response.message())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}