package com.example.onlinegroceries.ui.home.header.search

import com.example.onlinegroceries.R
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.databinding.ProductItemBinding
import com.example.onlinegroceries.model.ProductModelItem

class SearchAdapter(
    productsList: List<ProductModelItem>,
    val onSelect: (ProductModelItem) -> Unit,
) : BaseAdapter<ProductItemBinding, ProductModelItem>(productsList,onSelect) {
    override fun bindItem(ViewDataBinding: ProductItemBinding, data: ProductModelItem) {
        ViewDataBinding.model = data
        ViewDataBinding.root.setOnClickListener {
            onSelect(data)
        }
    }
    override val layoutId: Int = R.layout.product_item




}


