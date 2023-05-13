package com.example.onlinegroceries.utility

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

    @BindingAdapter("imageUrl")
    fun loadImage(view: View, imageUrl: String?) {
        val imageView: ImageView = view as ImageView
        Glide
            .with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

    @BindingAdapter("setPrice")
    fun setPrice(view: View, text: Double) {
        val textview: TextView = view as TextView
        textview.text = text.toString()

    }
