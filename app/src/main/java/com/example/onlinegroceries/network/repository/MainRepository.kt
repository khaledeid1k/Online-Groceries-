package com.example.onlinegroceries.network.repository


import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.network.dataSource.remoteDataSource.ApiHelper
import com.example.onlinegroceries.utility.Resource
import com.example.onlinegroceries.utility.wrapWithState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getProducts() : Resource<ProductModelResponse> {
        return    withContext(Dispatchers.Main) {
             wrapWithState { apiHelper.getProducts() }
        }

    }
    suspend fun getSearch(id: Int) = apiHelper.getSearch(id)
}