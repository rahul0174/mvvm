package com.example.api
import com.example.model.CitiesList
import com.example.model.CountriesList
import com.example.model.LoginRequest
import com.example.model.StateList
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface RetrofitService {
    @GET("hc/v1/getCountries") suspend fun getCountry():  Response<CountriesList>
    @GET("hc/v1/getStates/{country}") suspend fun getStates(@Path("country") country:Int?):Response<StateList>
    @GET("hc/v1/getCities/{state}") suspend fun getCities(@Path("state") state:Int?):Response<CitiesList>
    @POST("hc/v1/loginAsPatient") suspend fun getLoginRequest(@Body loginRequest: LoginRequest):Response<LoginRequest>
}