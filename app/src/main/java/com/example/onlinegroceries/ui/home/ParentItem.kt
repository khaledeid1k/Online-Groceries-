package com.example.onlinegroceries.ui.home

import com.example.onlinegroceries.model.ProductModelItem

sealed class ParentItem(val data: List<Any>?){
     class HEADER : ParentItem(null)
    data class EXCLUSIVEOFFER(val l:List<ProductModelItem>) : ParentItem(l)
    data class BESTSELLING(val l: List<ProductModelItem>) : ParentItem(l)
    data class GROCERIES(val l: List<ProductModelItem>) : ParentItem(l)
    data class BOTTOM_HOME (val l: List<ProductModelItem>) : ParentItem(l)
}
