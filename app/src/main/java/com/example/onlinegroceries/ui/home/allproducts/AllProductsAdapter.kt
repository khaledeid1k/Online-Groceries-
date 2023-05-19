package com.example.onlinegroceries.ui.home.allproducts

import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.model.ProductModelItem
import com.example.onlinegroceries.ui.home.ItemStandInteractionInter

class AllProductsAdapter(var  listenerProduct : ItemStandInteractionInter,
var list : List<ProductModelItem>) :
    BaseAdapter<ProductModelItem>(list,listenerProduct){
    override val layoutId: Int= R.layout.stand_item_home
}

