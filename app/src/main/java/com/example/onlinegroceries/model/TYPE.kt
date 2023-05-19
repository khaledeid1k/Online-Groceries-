package com.example.onlinegroceries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
enum class TYPE(var nameProduct : String) : Parcelable {
    MEN_CLOTHING("Men's Clothing"),
    ELECTRONICS("Electronics")
    ,JEWELRIES("Jewelery"),
    WOMEN_CLOTHING("Women's Clothing")
}