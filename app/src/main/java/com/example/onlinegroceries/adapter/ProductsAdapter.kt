package com.example.onlinegroceries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinegroceries.R
import com.example.onlinegroceries.databinding.ProductItemBinding
import com.example.onlinegroceries.network.data.ProductModel
import com.example.onlinegroceries.network.data.ProductResponse

class ProductsAdapter(
    private var productsList: ProductResponse,
    private var onSelect: ( ProductModel,Int) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    class ViewHolder(
        itemView: ProductItemBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        var productItemBinding: ProductItemBinding = itemView

        fun bind(
            yourDataType: ProductModel,
            onSelect: ( ProductModel,Int) -> Unit,
            position: Int
        ) {

            // bind your view here
            itemView.rootView.setOnClickListener {
                onSelect(yourDataType,position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val productItemBinding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.product_item, parent,
            false
        )
        return ViewHolder(productItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

     holder.productItemBinding.modelP = productsList[position]
       holder.bind(productsList,onSelect,position)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}


