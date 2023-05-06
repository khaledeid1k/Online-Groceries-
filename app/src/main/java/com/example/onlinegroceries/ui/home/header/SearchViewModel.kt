package com.example.onlinegroceries.ui.home.header

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.network.data.ProductModelItem
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.network.repository.MainRepository
import com.example.onlinegroceries.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel<ProductModelItem>() {
    // Backing property

     val id = MutableLiveData<String>()



    // if i want to cansel Coroutine

    //  search about product from Api
    override fun getAllProducts() {
        viewModelScope.launch {
            mainRepository.getSearch(id.value?.toInt()!!).let {
                withContext(Dispatchers.Main) {
                    if (it.isSuccessful) {
                        _productsList.postValue(Resource.success(it.body()))
                    } else {
                        _errorMessage.postValue(
                            Resource.error(it.errorBody().toString(), null).toString()
                        )
                    }
                }

            }

        }

    }




}