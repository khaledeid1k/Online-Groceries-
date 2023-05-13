package com.example.onlinegroceries.Trach

import androidx.recyclerview.widget.DiffUtil
import com.example.onlinegroceries.model.ProductModelItem

class MyDiffUtil (private val oldList : List<ProductModelItem>,
                  private val newList : List<ProductModelItem>):DiffUtil.Callback(){
    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return (oldList[oldItemPosition].id==newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}