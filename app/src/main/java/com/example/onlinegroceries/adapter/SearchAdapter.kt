package com.example.onlinegroceries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinegroceries.Network.data.ProductModel
import com.example.onlinegroceries.R

class SearchAdapter(
    var productsList: ProductModel, var context: Context
        )  : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var productImage=itemView.findViewById<ImageView>(R.id.productImage)!!
        var price=itemView.findViewById<TextView>(R.id.productPrice_TextView)!!
        var category=itemView.findViewById<TextView>(R.id.productType_TextView)!!
        var title=itemView.findViewById<TextView>(R.id.productName_TextView)!!


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.product_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productList=productsList[position]
        holder.title.text= productList.title
        holder.category.text= productList.category
        holder.price.text= productList.price.toString()
        val url: String = productList.image
        Glide
            .with(context)
            .load(url)
            .into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return productsList.size }

}


