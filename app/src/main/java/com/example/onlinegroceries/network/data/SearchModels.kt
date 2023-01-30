package com.example.onlinegroceries.network.data

data class SearchModels(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) {
    data class Rating(
        val count: Int,
        val rate: Double
    )
}