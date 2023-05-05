package com.example.onlinegroceries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.databinding.ProductItemBinding
import com.example.onlinegroceries.network.data.ProductModel
import com.example.onlinegroceries.network.data.ProductResponse

class ProductsAdapter(
     productsList: ProductModel,
     val  onSelect: (ProductModel.ProductModelItem) -> Unit,
    ) : BaseAdapter<ProductItemBinding, ProductModel.ProductModelItem>(productsList) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ProductItemBinding
        get() = ProductItemBinding::inflate

    override fun bindItem(viewBinding: ProductItemBinding, data: ProductModel.ProductModelItem) {
        viewBinding.modelP = data
        viewBinding.root.setOnClickListener {
            onSelect(data)
        }

    }




}




