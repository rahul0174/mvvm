package com.example.api

import com.example.repository.ExceptionHandler


abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall:suspend() -> retrofit2.Response<T>):ExceptionHandler<T> {
        try {
            val response=apiCall()
            if (response.isSuccessful) {
                if (response.code() in 200..209) {
                    val body=response.body()
                    body?.let {
                        return ExceptionHandler.Success(it) }
                }
            }
            return ExceptionHandler.Error("${response.code()} ${response.message()}")
        }catch (e:Exception){
            return ExceptionHandler.Error(e.message?:e.toString())
        }
    }
}