package com.example.onlinegroceries.Network.Repository


import com.example.onlinegroceries.Network.dataSource.remoteDataSource.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class MainRepository @Inject constructor()
{
    suspend fun getProducts() = RetrofitBuilder.apiService.getProducts()

}