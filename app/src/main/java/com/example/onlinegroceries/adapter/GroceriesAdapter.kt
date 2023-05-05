package com.example.onlinegroceries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.databinding.GroceriesItemBinding
import com.example.onlinegroceries.network.data.ProductModel

class GroceriesAdapter (
      productsList: ProductResponse,
    var onSelect: (ProductModel.ProductModelItem) -> Unit,
) :
    BaseAdapter<GroceriesItemBinding, ProductModel.ProductModelItem>(productsList) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> GroceriesItemBinding
    get() = GroceriesItemBinding::inflate
    override fun bindItem(viewBinding: GroceriesItemBinding, data: ProductModel.ProductModelItem) {
        viewBinding.model = data
        viewBinding.root.setOnClickListener {
            onSelect(data)
        }
    }


}

