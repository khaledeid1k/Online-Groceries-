package com.example.onlinegroceries.network.dataSource.remoteDataSource

import com.example.onlinegroceries.network.data.ProductModelItem
import com.example.onlinegroceries.network.data.ProductModelResponse
import com.example.onlinegroceries.network.data.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    // this interface responsible for end point according for function you add above of
    // method
    @GET("Products")
    suspend fun getProducts(): Response<ProductModelResponse>
    @GET("Products/{id}")
    suspend fun getSearch(@Path("id") id: Int): Response<ProductModelItem>
}


