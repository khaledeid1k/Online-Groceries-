package com.example.onlinegroceries.ui.home.showproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
import com.example.onlinegroceries.network.Resource

class ShowProductViewModel (savedStateHandle: SavedStateHandle): BaseViewModel() {

    val argument=savedStateHandle.let {
        ShowProductArgs.fromSavedStateHandle(it)
    }
    private val _productsList = MutableLiveData<ProductModelItem>()
    val productsList: LiveData<ProductModelItem> get() = _productsList
    init {
        _productsList.postValue(argument.productModel)
    }


}