package com.example.onlinegroceries.network

import com.example.onlinegroceries.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


// Tells Dagger this is a Dagger module
// Because of @Binds, StorageModule needs to be an abstract class
//@Module
//@InstallIn(SingletonComponent::class)
object Network {


    private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().readTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    private fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    val retrofit: ApiService = provideRetrofit().create(ApiService::class.java)


}



