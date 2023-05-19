package com.example.onlinegroceries.utility

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinegroceries.base.BaseAdapter
import com.example.onlinegroceries.model.ParentItem
import com.example.onlinegroceries.ui.home.ItemStandAdapter
import com.example.onlinegroceries.ui.home.ItemSleepAdapter
import com.example.onlinegroceries.utility.extention.makeGone
import com.example.onlinegroceries.utility.extention.makeVisible

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

@BindingAdapter(value = ["app:setList"])
fun <T> setRecyclerView(view: RecyclerView, list: List<T>?) {
    if (list != null) {
        (view.adapter as BaseAdapter<T>?)?.setItems(list)
    } else {
        (view.adapter as BaseAdapter<T>?)?.setItems(mutableListOf())

    }
}

@BindingAdapter("app:setAdapter")
fun setAdapter(view: RecyclerView, dataItem: ParentItem?) {
    view.adapter= when (dataItem) {
        is ParentItem.ELECTRONICS -> ItemStandAdapter(

            dataItem.electronicsTag.ResourcesData,
            dataItem.listener
        )

        is ParentItem.MEN_CLOTHING -> ItemStandAdapter(

            dataItem.menTag.ResourcesData,
            dataItem.listener
        )

        is ParentItem.WOMEN_CLOTHING -> ItemSleepAdapter(

            dataItem.WomenTag.ResourcesData,
            dataItem.listener
        )
        is ParentItem.JEWELRIES -> ItemSleepAdapter(

            dataItem.JewelriesTag.ResourcesData,
            dataItem.listener
        )
        else -> null

    }

}
@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, state: MediatorLiveData<T>?) {
    if (state?.value==null) view.makeVisible()  else view.makeGone()
}

