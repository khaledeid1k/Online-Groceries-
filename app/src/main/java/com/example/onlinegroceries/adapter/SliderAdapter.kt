package com.example.onlinegroceries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.onlinegroceries.network.data.SliderData
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(
    var context: Context?,
    private var sliderDataArrayList: ArrayList<SliderData>?

) : SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    // list for storing urls of images.


    class SliderAdapterViewHolder(itemView: View?) : ViewHolder(itemView) {


        val imageViewBackground =
            itemView?.findViewById<ImageView>(com.example.onlinegroceries.R.id.myimage)


    }

    override fun getCount(): Int {
        return sliderDataArrayList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterViewHolder {
        val view = LayoutInflater.from(parent!!.context)
            .inflate(com.example.onlinegroceries.R.layout.slider_layout, parent, false)
        return SliderAdapterViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder?, position: Int) {
        val sliderItem = sliderDataArrayList?.get(position)

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder!!.itemView)
            .load(sliderItem!!.image)
            .fitCenter()
            .into(viewHolder.imageViewBackground!!)
    }
}