package com.example.onlinegroceries.Network.Repository


import com.example.onlinegroceries.Network.dataSource.remoteDataSource.RetrofitBuilder

class MainRepository(retrofitService: RetrofitBuilder) {

    suspend fun getProducts() = RetrofitBuilder.apiService.getProducts()

}