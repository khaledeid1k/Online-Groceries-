package com.example.onlinegroceries.utility

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

 suspend fun <T> wrapWithState(function: suspend () -> Response<T>): Resource<T> {
    return withContext(Dispatchers.IO) {
        val response = function()
        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.errorBody().toString(), response.body())
        }
    }
}