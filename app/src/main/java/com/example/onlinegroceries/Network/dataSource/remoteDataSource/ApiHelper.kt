package com.example.onlinegroceries.Network.dataSource.remoteDataSource

import com.example.onlinegroceries.Network.data.ProductResponse
import com.example.onlinegroceries.Network.data.SearchModel
import com.example.onlinegroceries.Network.data.SearchResponse
import retrofit2.Response
//will help ApiService to be accessed via repository maintaining encapsulation
interface ApiHelper {
    suspend fun getProducts(): Response<ProductResponse>
    suspend fun getSearch(id :Int): Response<SearchModel>

}