package com.example.onlinegroceries.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.network.repository.MainRepository
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel
@Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<ProductModelResponse>() {


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