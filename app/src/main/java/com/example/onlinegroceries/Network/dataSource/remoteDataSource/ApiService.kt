package com.example.onlinegroceries.Network.dataSource.remoteDataSource

import com.example.onlinegroceries.Network.data.ProductResponse
import com.example.onlinegroceries.Network.data.SearchModel
import com.example.onlinegroceries.Network.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("Products")
    suspend fun getProducts() : Response<ProductResponse>


    @GET("Products/{id}")
    suspend fun getSearch(
        @Path("id")  id: Int
    ) : Response<SearchModel>
}


