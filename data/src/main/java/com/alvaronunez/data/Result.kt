package com.alvaronunez.data

// TODO: 23/10/2020 Use Either?
sealed class Result<out T : Any> {

    class Response<out T : Any>(val data: T) : Result<T>()

    class Error(val error: String? = null) : Result<Nothing>()

}
