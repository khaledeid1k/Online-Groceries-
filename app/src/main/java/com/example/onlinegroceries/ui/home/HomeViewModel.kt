package com.example.onlinegroceries.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.onlinegroceries.base.BaseViewModel
import com.example.onlinegroceries.model.ParentItem
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.model.ProductModelResponse
import com.example.onlinegroceries.model.TYPE
import com.example.onlinegroceries.model.Tag
import com.example.onlinegroceries.network.Resource
import com.example.onlinegroceries.utility.Event
import kotlinx.coroutines.launch

class HomeViewModel :
    BaseViewModel(),
    ItemStandInteractionInter,
    ItemSleepInteractionInter {
    private val _errorMessage = MutableLiveData<String>()
    private val _productsList = MediatorLiveData<List<ParentItem>?>()
    val errorMessage: LiveData<String> get() = _errorMessage
    val productsList: MediatorLiveData<List<ParentItem>?> get() = _productsList


    val list = mutableListOf<ParentItem>()

    private val _jewelery = MutableLiveData<Resource<ProductModelResponse>>()
    private val _electronics = MutableLiveData<Resource<ProductModelResponse>>()
    private val _menClothing = MutableLiveData<Resource<ProductModelResponse>>()
    private val _womenClothing = MutableLiveData<Resource<ProductModelResponse>>()

    private val _itemSleep = MutableLiveData<Event<ProductModelItem>>()
    val itemSleep: LiveData<Event<ProductModelItem>> get() = _itemSleep

    val itemStand: LiveData<Event<ProductModelItem>> get() = _itemStand
    private val _itemStand = MutableLiveData<Event<ProductModelItem>>()

    private val _navigateToDetails = MutableLiveData<Boolean>()

    val navigateToDetails : LiveData<Boolean>
        get() = _navigateToDetails




    init {
        _productsList.postValue(null)
        _productsList.addSource(_electronics) { updateData() }
        _productsList.addSource(_jewelery) { updateData() }
        _productsList.addSource(_menClothing) { updateData() }
        _productsList.addSource(_womenClothing) { updateData() }

        viewModelScope.launch {
            callApiAndHandleResponse(repository::getJewelery, _jewelery)
            callApiAndHandleResponse(repository::getElectronics, _electronics)
            callApiAndHandleResponse(repository::getMenClothing, _menClothing)
            callApiAndHandleResponse(repository::getWomenClothing, _womenClothing)


        }


    }

    private fun updateData() {

        val data = listOf(
            ParentItem.HEADER,
            ParentItem.MEN_CLOTHING(
                Tag("Men\'s Clothing", _menClothing.value?.data ?: emptyList()),
                this
            ),
            ParentItem.ELECTRONICS(
                Tag("Electronics", _electronics.value?.data ?: emptyList()),
                this
            ),
            ParentItem.JEWELRIES(Tag("Jewelery", _jewelery.value?.data ?: emptyList()), this),
            ParentItem.WOMEN_CLOTHING(
                Tag(
                    "Women\'s Clothing",
                    _womenClothing.value?.data ?: emptyList()
                ), this
            ),
        )
        _productsList.postValue(data)

    }

    override fun onCleared() {
        super.onCleared()
        _productsList.removeSource(_menClothing)
        _productsList.removeSource(_menClothing)
        _productsList.removeSource(_jewelery)
        _productsList.removeSource(_womenClothing)
    }

    override fun onClickItemStand(productModelItem: ProductModelItem) {
        _itemStand.postValue( Event(productModelItem) )
    }

    override fun onClickItemSleep(productModelItem: ProductModelItem) {
        _itemSleep.postValue(Event(productModelItem))
    }


}
