package com.sedra.fitroad.data.helper

import android.widget.Toast
import androidx.fragment.app.Fragment

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()
    object Idle : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val msg: String?, val responseCode: String? = null) : Resource<T>()
    class NetworkError<out T> : Resource<T>()
    class ServerError<out T> : Resource<T>()
}


fun Fragment.showToast(msg: String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}