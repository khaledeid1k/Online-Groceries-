package com.example.onlinegroceries.ui.home.allproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.model.ProductModelResponse
import com.example.onlinegroceries.model.TYPE
import com.example.onlinegroceries.network.Resource
import kotlinx.coroutines.launch

class AllProductsViewModel (private val savedStateHandle: SavedStateHandle): BaseViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    private val _productsList = MutableLiveData<Resource<ProductModelResponse>>()

    val errorMessage: LiveData<String> get() = _errorMessage
    val productsList: LiveData<Resource<ProductModelResponse>> get() = _productsList

    private val _argumentValue =savedStateHandle.let {
        FragmentAllProductsArgs.fromSavedStateHandle(it)
    }
    val nameProduct get() = _argumentValue.nameOfData.nameProduct


    private fun getSingleCharacter() {
        val type = _argumentValue.nameOfData

        viewModelScope.launch {
            when(type){
                TYPE.MEN_CLOTHING -> callApiAndHandleResponse(repository::getMenClothing, _productsList)
                TYPE.ELECTRONICS ->  callApiAndHandleResponse(repository::getElectronics, _productsList)
                TYPE.JEWELRIES ->  callApiAndHandleResponse(repository::getJewelery, _productsList)
                TYPE.WOMEN_CLOTHING ->  callApiAndHandleResponse(repository::getWomenClothing, _productsList)
            }

        }
    }

    init {
        getSingleCharacter()
    }
}