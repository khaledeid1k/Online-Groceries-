package com.example.onlinegroceries.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinegroceries.repository.MainRepository
import com.example.onlinegroceries.network.Resource
import com.example.onlinegroceries.network.Status
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {
    val repository  by lazy { MainRepository() }
    private var job: Job? = null

    suspend fun <T> callApiAndHandleResponse(apiCall: suspend () -> Resource<T>, liveData: MutableLiveData<Resource<T>>) {
        val result = apiCall()
        when (result.status) {
            Status.SUCCESS -> liveData.postValue(Resource.success(result.data))
            Status.ERROR -> liveData.postValue(Resource.error(result.message.toString(), null))
            Status.LOADING ->  liveData.postValue(Resource.loading(null))
        }
    }


    // if i want to cansel Coroutine
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }





}