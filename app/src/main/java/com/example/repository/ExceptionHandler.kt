package com.example.repository

sealed class ExceptionHandler<T>(val data:T?=null, val errorMessage: String?=null) {
    class Loading<T>:ExceptionHandler<T>()
    class Success<T>(data: T?=null):ExceptionHandler<T>(data=data)
    class Error<T>( errorMessage:String):ExceptionHandler<T>(errorMessage=errorMessage)
}