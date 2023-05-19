package com.example.onlinegroceries.network

import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
import retrofit2.Response

// ApiHelperImpl  will be the class which will implement ApiHelper
// to provide functionality to ApiHelper functions.
class ApiHelperImpl: ApiHelper {
    override suspend fun getProducts(): Response<ProductModelResponse> =
        Network.retrofit.getProducts()

    override suspend fun getSearch(id: Int): Response<ProductModelItem> =
        Network.retrofit.getSearch(id)

    override suspend fun getElectronics(): Response<ProductModelResponse> =
        Network.retrofit.getElectronics()


    override suspend fun getJewelery(): Response<ProductModelResponse> =
        Network.retrofit.getJewelery()


    override suspend fun getMenClothing(): Response<ProductModelResponse> =
        Network.retrofit.getMenClothing()

    override suspend fun getWomenClothing(): Response<ProductModelResponse> =
        Network.retrofit.getWomenClothing()


}