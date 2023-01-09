package com.example.onlinegroceries.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.Network.Repository.MainRepository
import com.example.onlinegroceries.Network.data.ProductModel
import com.example.onlinegroceries.Network.data.ProductResponse
import com.example.onlinegroceries.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel
class ProductsViewModel
  @Inject constructor(private val mainRepository: MainRepository)
    : ViewModel() {
    // Backing property
    private var _errorMessage = MutableLiveData<String>()
    private var _productsList = MutableLiveData<Resource<ProductResponse>>()

    val errorMessage : MutableLiveData<String> get() = _errorMessage
    val productsList : MutableLiveData<Resource<ProductResponse>>get() = _productsList
    // if i want to cansel Coroutine
    private var job: Job? = null
init {
    getAllProducts()
}

    private fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO ).launch {
            _productsList.postValue(Resource.loading(null))

            mainRepository .getProducts().let {
                withContext(Dispatchers.Main) {
                    if (it.isSuccessful) {
                        _productsList.postValue(Resource.success(it.body()))
                    } else {
                        _errorMessage.postValue(Resource.error(it.errorBody().toString(), null).toString())
                    }
                }
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}