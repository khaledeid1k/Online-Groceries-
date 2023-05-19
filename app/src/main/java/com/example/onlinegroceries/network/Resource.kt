package com.example.onlinegroceries.network

//These classes will be used to wrap our
// data to be used in a generic way into our UI.
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val isLoading: Boolean
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null,false)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg,false)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null,true)
        }

    }

}
