package com.example.onlinegroceries.Network.Repository


import com.example.onlinegroceries.Network.dataSource.remoteDataSource.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

//@InstallIn(SingletonComponent::class)
//@Module
class MainRepository //@Inject constructor()
{
  //  @Provides
    suspend fun getProducts() = RetrofitBuilder.apiService.getProducts()

}