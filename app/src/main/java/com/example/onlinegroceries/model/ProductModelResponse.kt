package com.example.onlinegroceries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class ProductModelResponse: ArrayList<ProductModelItem>()

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
        val count: Int, val rate: Double
    ) : Parcelable
}

