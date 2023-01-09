package com.example.onlinegroceries.Network.dataSource.remoteDataSource

import com.example.onlinegroceries.Network.data.ProductModel
import com.example.onlinegroceries.Network.data.ProductResponse
import retrofit2.Response
//will help ApiService to be accessed via repository maintaining encapsulation
interface ApiHelper {
    suspend fun getProducts(): Response<ProductResponse>

}