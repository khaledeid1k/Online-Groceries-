package com.example.onlinegroceries.ui.home.header.search

import androidx.lifecycle.MutableLiveData
import com.example.onlinegroceries.base.BaseViewModel

class SearchViewModel :        BaseViewModel()

{

    // if i want to cansel Coroutine
    val id = MutableLiveData<String>()
      fun getData() {
        TODO("Not yet implemented")
    }

//    init {
//        getAllProducts()
 //   }

//    override suspend fun getData() {
//        TODO("Not yet implemented")
//    }

//     fun getAllProducts() {
//        viewModelScope.launch {
//            repository.getSearch(id.value?.toInt()!!).let {
//
//
//                _productsList.postValue(Resource.success(it.data))
//
//                _errorMessage.postValue(Resource.error(it.message.toString(), null).toString())
//
//
//            }
//
//
//        }
//    }



}