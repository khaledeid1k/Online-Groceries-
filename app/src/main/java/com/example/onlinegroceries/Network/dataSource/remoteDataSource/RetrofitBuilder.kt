package com.example.onlinegroceries.Network.dataSource.remoteDataSource

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module
    object RetrofitBuilder {

        private const val  BASE_URL = "https://fakestoreapi.com/"
//    @Provides
//    @Singleton
private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: ApiService = getRetrofit().create(ApiService::class.java)


    }


