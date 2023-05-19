package com.example.onlinegroceries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Tag(
    val title: String,
    val ResourcesData: List<ProductModelItem>
)