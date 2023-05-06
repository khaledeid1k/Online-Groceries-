package com.example.onlinegroceries.network.dataSource.remoteDataSource

import com.example.onlinegroceries.network.data.ProductModelItem
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.network.data.ProductResponse
import retrofit2.Response

//will help ApiService to be accessed via repository maintaining encapsulation
interface ApiHelper {
    suspend fun getProducts(): Response<ProductModelResponse>
    suspend fun getSearch(id: Int): Response<ProductModelItem>

}