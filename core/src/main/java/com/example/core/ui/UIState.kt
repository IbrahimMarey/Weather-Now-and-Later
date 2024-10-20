package com.example.core.ui


sealed class UIState<out T> {
    data object Fetch : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message:String) : UIState<Nothing>()
}
