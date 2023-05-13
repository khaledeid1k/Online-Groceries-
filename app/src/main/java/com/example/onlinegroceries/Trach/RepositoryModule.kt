package com.example.onlinegroceries.Trach

import com.example.onlinegroceries.network.Network
import com.example.onlinegroceries.network.ApiHelper
import com.example.onlinegroceries.network.ApiHelperImpl
import com.example.onlinegroceries.network.ApiService


interface RepositoryModule {

    fun provideApiService(): ApiService = Network.retrofit

    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}