package com.example.onlinegroceries.ui.home.header

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinegroceries.network.repository.MainRepository
import com.example.onlinegroceries.network.data.SearchModels
import com.example.onlinegroceries.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    // Backing property
    private var _errorMessage = MutableLiveData<String>()
    private var _productsList = MutableLiveData<Resource<SearchModels>>()
     val id = MutableLiveData<String>()

    val errorMessage: LiveData<String> get() = _errorMessage
    val productsList: LiveData<Resource<SearchModels>> get() = _productsList

    // if i want to cansel Coroutine
    private var job: Job? = null

    //  search about product from Api
    fun getSearch() {
        viewModelScope.launch {
            mainRepository.getSearch(id.value?.toInt()!!).let {
                withContext(Dispatchers.Main) {
                    if (it.isSuccessful) {
                        _productsList.postValue(Resource.success(it.body()))
                    } else {
                        _errorMessage.postValue(
                            Resource.error(it.errorBody().toString(), null).toString()
                        )
                    }
                }

            }

        }

    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}