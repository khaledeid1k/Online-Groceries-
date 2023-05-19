package com.example.onlinegroceries.Trach

//These classes will be used to wrap our
// data to be used in a generic way into our UI.
sealed class Statuss<T> {
    data class Success<T>(val data:T): Statuss<T>()
    data class Error(val message:String): Statuss<Nothing>()
    object Loading : Statuss<Nothing>()

}