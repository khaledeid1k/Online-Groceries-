package com.example.onlinegroceries.network

import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
import retrofit2.Response
import retrofit2.http.GET

//will help ApiService to be accessed via repository maintaining encapsulation
interface ApiHelper {
    suspend fun getProducts(): Response<ProductModelResponse>
    suspend fun getSearch(id: Int): Response<ProductModelItem>
    suspend fun getElectronics(): Response<ProductModelResponse>
    suspend fun getJewelery(): Response<ProductModelResponse>
    suspend fun getMenClothing(): Response<ProductModelResponse>
    suspend fun getWomenClothing(): Response<ProductModelResponse>

}