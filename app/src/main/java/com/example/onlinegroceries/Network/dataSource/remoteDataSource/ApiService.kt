package com.example.onlinegroceries.Network.dataSource.remoteDataSource

import com.example.onlinegroceries.Network.data.ProductModel
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton



interface ApiService {
    @GET("Products")
    suspend fun getProducts() : Response<ProductModel>
}


