package com.example.onlinegroceries.Network.Repository


import com.example.onlinegroceries.Network.dataSource.remoteDataSource.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){
    suspend fun getProducts() = apiHelper.getProducts()
}