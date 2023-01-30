package com.example.onlinegroceries.network.dataSource.remoteDataSource

import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.network.data.SearchModels
import retrofit2.Response

//will help ApiService to be accessed via repository maintaining encapsulation
interface ApiHelper {
    suspend fun getProducts(): Response<ProductResponse>
    suspend fun getSearch(id: Int): Response<SearchModels>

}