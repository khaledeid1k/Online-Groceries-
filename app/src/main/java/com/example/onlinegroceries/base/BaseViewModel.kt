package com.example.onlinegroceries.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.utility.Resource
import kotlinx.coroutines.Job

abstract class BaseViewModel<T> : ViewModel() {

    var _errorMessage = MutableLiveData<String>()
    var _productsList = MutableLiveData<Resource<T>>()

    val errorMessage: LiveData<String> get() = _errorMessage
    val productsList: LiveData<Resource<T>> get() = _productsList

    var job: Job? = null


    abstract fun getAllProducts()

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }




}