package com.example.onlinegroceries.ui.home

import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.model.ProductModelResponse
import com.example.onlinegroceries.repository.MainRepository
import com.example.onlinegroceries.utility.Resource
import kotlinx.coroutines.*

class ProductsViewModel
 :
    BaseViewModel<ProductModelResponse>() {
    private val mainRepository= MainRepository()

    // if i want to cansel Coroutine

    init {
        getAllProducts()
    }

    override fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO).launch {
            _productsList.postValue(Resource.loading(null))

            mainRepository.getProducts().let {


                        _productsList.postValue(Resource.success(it.data))

                        _errorMessage.postValue(Resource.error(it.message.toString(), null).toString())


            }

        }
    }



}