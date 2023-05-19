package com.example.onlinegroceries.repository


import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
import com.example.onlinegroceries.network.ApiHelperImpl
import com.example.onlinegroceries.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainRepository {
    private val apiHelper = ApiHelperImpl()
    suspend fun getProducts(): Resource<ProductModelResponse> =
        wrapWithState { apiHelper.getProducts() }


    suspend fun getElectronics(): Resource<ProductModelResponse> =
        wrapWithState { apiHelper.getElectronics() }


    suspend fun getJewelery(): Resource<ProductModelResponse> =
        wrapWithState { apiHelper.getJewelery() }


    suspend fun getMenClothing(): Resource<ProductModelResponse> =
        wrapWithState { apiHelper.getMenClothing() }


    suspend fun getWomenClothing(): Resource<ProductModelResponse> =
        wrapWithState { apiHelper.getWomenClothing() }


    suspend fun getSearch(id: Int): Resource<ProductModelItem> =
        wrapWithState { apiHelper.getSearch(id) }


    private suspend fun <T> wrapWithState(function: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            Resource.loading(data = null)
            val response = function()
            withContext(Dispatchers.Main)
            {
                if (response.isSuccessful) {
                    Resource.success(response.body())
                } else {
                    Resource.error(response.errorBody().toString(), response.body())
                }
            }
        }


    }


}

