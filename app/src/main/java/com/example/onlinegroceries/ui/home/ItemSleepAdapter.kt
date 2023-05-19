package com.example.onlinegroceries.ui.home

import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.base.BaseInteractionInter
import com.example.onlinegroceries.model.ProductModelItem

class ItemSleepAdapter (
    productsList: List<ProductModelItem>,
    listener : ItemSleepInteractionInter

) : BaseAdapter<ProductModelItem>(productsList,listener) {


    override val layoutId: Int = R.layout.sleep_item_home



}
interface ItemSleepInteractionInter : BaseInteractionInter {
    fun onClickItemSleep(productModelItem: ProductModelItem)

}






