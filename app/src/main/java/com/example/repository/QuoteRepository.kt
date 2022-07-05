package com.example.repository

import com.example.api.BaseApiResponse
import com.example.api.QuoteDataBase
import com.example.api.RetrofitService
import com.example.model.CitiesList
import com.example.model.CountriesList
import com.example.model.LoginRequest
import com.example.model.StateList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuoteRepository (private val retrofitService: RetrofitService,private val quoteDataBase: QuoteDataBase): BaseApiResponse() {

    suspend fun getCountryList(): Flow<ExceptionHandler<CountriesList>> {
        return flow {
            emit(safeApiCall { retrofitService.getCountry() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getStatesList(countryId:Int?): Flow<ExceptionHandler<StateList>> {
        return flow {
            emit(safeApiCall { retrofitService.getStates(countryId) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCitiesList(statesId:Int?): Flow<ExceptionHandler<CitiesList>> {
        return flow { emit(safeApiCall { retrofitService.getCities(statesId) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getLoginRequest(loginRequest: LoginRequest):Flow<ExceptionHandler<LoginRequest>>{
        return flow {
            emit(safeApiCall { retrofitService.getLoginRequest(loginRequest) })
        }.flowOn(Dispatchers.IO)
    }

}