package com.example.onlinegroceries.network.data

sealed class ParentItem<T>(val list: T) {
    data class HEADER(val l: ProductModel) : ParentItem<ProductModel>(l)
    data class EXCLUSIVEOFFER(val l: ProductModel) : ParentItem<ProductModel>(l)
    data class BESTSELLING(val l: ProductModel) : ParentItem<ProductModel>(l)
    data class GROCERIES(val l: ProductModel) : ParentItem<ProductModel>(l)
    data class BOTTOM_HOME(val l: ProductModel) : ParentItem<ProductModel>(l)
}
