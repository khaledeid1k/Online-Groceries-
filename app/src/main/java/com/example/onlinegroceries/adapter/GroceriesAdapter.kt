package com.example.onlinegroceries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinegroceries.network.data.ProductResponse
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.GroceriesItemBinding

class GroceriesAdapter (
    private var productsList: ProductResponse,
    var context: Context
) : RecyclerView.Adapter<GroceriesAdapter.ViewHolder>() {



    class ViewHolder(itemView: GroceriesItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
         var  groceriesItemBinding :GroceriesItemBinding=itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val groceriesItemBinding: GroceriesItemBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),R.layout.groceries_item,parent,false
        )
        return ViewHolder(groceriesItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productList = productsList[position]
        holder.groceriesItemBinding.model=productList
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}

