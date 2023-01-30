package com.example.onlinegroceries.network.repository


import com.example.onlinegroceries.network.dataSource.remoteDataSource.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getProducts() = apiHelper.getProducts()
    suspend fun getSearch(id: Int) = apiHelper.getSearch(id)
}