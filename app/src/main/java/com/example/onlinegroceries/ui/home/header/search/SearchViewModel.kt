package com.example.onlinegroceries.ui.home.header.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.repository.MainRepository
import com.example.onlinegroceries.utility.Resource
import kotlinx.coroutines.launch

class SearchViewModel (private val mainRepository: MainRepository) :
    BaseViewModel<ProductModelItem>() {


    // if i want to cansel Coroutine
    val id = MutableLiveData<String>()

    init {
        getAllProducts()
    }

    override fun getAllProducts() {
        viewModelScope.launch {
            mainRepository.getSearch(id.value?.toInt()!!).let {


                _productsList.postValue(Resource.success(it.data))

                _errorMessage.postValue(Resource.error(it.message.toString(), null).toString())


            }


        }
    }



}