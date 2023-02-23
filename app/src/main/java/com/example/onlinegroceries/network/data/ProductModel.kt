package com.example.onlinegroceries.network.data
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.parcelize.Parcelize

@Parcelize
open class ProductModel :
    ArrayList<ProductModel.ProductModelItem>(), Parcelable {
    @Parcelize
    data class ProductModelItem(
        val category: String,
        val description: String,
        val id: Int,
        val image: String,
        val price: Double,
        val rating: Rating,
        val title: String
    ) : Parcelable {
        @Parcelize
        data class Rating(
            val count: Int,
            val rate: Double
        ) : Parcelable
    }

    companion object{
            @BindingAdapter("imageUrl")
            @JvmStatic
            fun loadImage(view:View,imageUrl:String?){
                val imageView :ImageView = view as ImageView
                Glide
                    .with(imageView.context)
                    .load(imageUrl)
                    .into(imageView)
            }

        @BindingAdapter("setPrice")
        @JvmStatic
        fun setPrice(view:View, text: Double) {
            val textview : TextView = view as TextView
            textview.text= text.toString()

        }
    }
}