package com.example.onlinegroceries.Network.Repository

import com.example.onlinegroceries.Network.dataSource.remoteDataSource.ApiHelper
import com.example.onlinegroceries.Network.dataSource.remoteDataSource.ApiHelperImpl
import com.example.onlinegroceries.Network.dataSource.remoteDataSource.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {



    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}