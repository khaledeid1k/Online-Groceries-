package com.example.onlinegroceries.ui.home

import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.base.BaseInteractionInter
import com.example.onlinegroceries.model.ProductModelItem

class ItemStandAdapter(
    productsList: List<ProductModelItem>,
    listener : ItemStandInteractionInter
) : BaseAdapter<ProductModelItem>(productsList,listener) {

    override val layoutId: Int = R.layout.stand_item_home



}

interface ItemStandInteractionInter : BaseInteractionInter {
    fun onClickItemStand(productModelItem: ProductModelItem)

}




