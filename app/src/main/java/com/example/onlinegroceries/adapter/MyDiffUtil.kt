package com.example.onlinegroceries.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.onlinegroceries.network.data.ProductModel
import com.example.onlinegroceries.network.data.ProductResponse

class MyDiffUtil (private val oldList : List<ProductModel.ProductModelItem>,
                  private val newList : List<ProductModel.ProductModelItem>):DiffUtil.Callback(){
    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return (oldList[oldItemPosition].id==newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}