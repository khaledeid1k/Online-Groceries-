package com.example.onlinegroceries.repository


import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
import com.example.onlinegroceries.network.ApiHelperImpl
import com.example.onlinegroceries.utility.Resource
import com.example.onlinegroceries.utility.wrapWithState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository
     {
         private val apiHelper= ApiHelperImpl()
     suspend fun getProducts(): Resource<ProductModelResponse> {
        return withContext(Dispatchers.Main) {
            wrapWithState { apiHelper.getProducts() }
        }

    }

     suspend fun getSearch(id: Int): Resource<ProductModelItem> {
        return withContext(Dispatchers.Main) {
            wrapWithState { apiHelper.getSearch(id) }
        }
    }
}

