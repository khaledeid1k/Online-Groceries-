package com.example.onlinegroceries.network

import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
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

    @GET("Products/category/electronics")
    suspend fun getElectronics(): Response<ProductModelResponse>

    @GET("Products/category/jewelery")
    suspend fun getJewelery(): Response<ProductModelResponse>

    @GET("Products/category/men's clothing")
    suspend fun getMenClothing(): Response<ProductModelResponse>

    @GET("Products/category/women's clothing")
    suspend fun getWomenClothing(): Response<ProductModelResponse>
}


