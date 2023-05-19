package com.example.onlinegroceries.utility.extention

import android.view.View


fun View.makeInVisible(){
    this.visibility = View.INVISIBLE
}

fun View.makeVisible(){
    this.visibility = View.VISIBLE
}
fun View.makeGone(){
    this.visibility=View.GONE
}