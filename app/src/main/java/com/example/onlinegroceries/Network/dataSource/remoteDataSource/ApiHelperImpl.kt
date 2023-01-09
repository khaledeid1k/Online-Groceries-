package com.example.onlinegroceries.Network.dataSource.remoteDataSource

import com.example.onlinegroceries.Network.data.ProductModel
import com.example.onlinegroceries.Network.data.ProductResponse
import retrofit2.Response
import javax.inject.Inject
// ApiHelperImpl  will be the class which will implement ApiHelper
// to provide functionality to ApiHelper functions.
class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService):ApiHelper{
    override suspend fun getProducts(): Response<ProductResponse> =
        apiService.getProducts()
}