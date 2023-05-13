package com.example.onlinegroceries.ui.home.jewelery

import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.databinding.JeweleryItemBinding
import com.example.onlinegroceries.model.ProductModelItem

class JeweleryAdapter (
    productsList: List<ProductModelItem>,
    val onSelect: (ProductModelItem) -> Unit,
) : BaseAdapter<JeweleryItemBinding, ProductModelItem>(productsList,onSelect) {

    override fun bindItem(ViewDataBinding: JeweleryItemBinding, data: ProductModelItem) {
        ViewDataBinding.model = data
        ViewDataBinding.root.setOnClickListener {
            onSelect(data)
        }
    }
    override val layoutId: Int = R.layout.jewelery_item



}




