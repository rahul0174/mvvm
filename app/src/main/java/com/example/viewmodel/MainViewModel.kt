package com.example.viewmodel

import androidx.lifecycle.*
import com.example.model.CitiesList
import com.example.model.CountriesList
import com.example.model.LoginRequest
import com.example.model.StateList
import com.example.repository.QuoteRepository
import com.example.repository.ExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

 class MainViewModel(private val repository: QuoteRepository):ViewModel() {

     private val mutableLiveDataCountry: MutableLiveData<ExceptionHandler<CountriesList>> = MutableLiveData()
     val liveDataCounty: LiveData<ExceptionHandler<CountriesList>> = mutableLiveDataCountry

     private val mutableLiveDataStateList: MutableLiveData<ExceptionHandler<StateList>> = MutableLiveData()
     val liveDataStateList: LiveData<ExceptionHandler<StateList>> = mutableLiveDataStateList

     private val mutableLiveDataCitiesList: MutableLiveData<ExceptionHandler<CitiesList>> = MutableLiveData()
     val liveDataCitiesList: LiveData<ExceptionHandler<CitiesList>> = mutableLiveDataCitiesList
     
     private val mutableLiveDataLoginRequest:MutableLiveData<ExceptionHandler<LoginRequest>> = MutableLiveData()
     val liveDataLoginRequest:LiveData<ExceptionHandler<LoginRequest>> = mutableLiveDataLoginRequest

     init {
         viewModelScope.launch {
             repository.getCountryList().collect { values ->
                 mutableLiveDataCountry.value = values
             }
         }
     }

     fun findByStateID(id: Int) = viewModelScope.launch {
         repository.getStatesList(id).collect { values ->
             mutableLiveDataStateList.value = values
         }
     }

     fun findByCitiesID(id: Int)=viewModelScope.launch {
         repository.getCitiesList(id).collect { values ->
             mutableLiveDataCitiesList.value = values
         }

     }
     fun sendLoginData(loginRequest: LoginRequest)=viewModelScope.launch {
         repository.getLoginRequest(loginRequest).collect{values ->
             mutableLiveDataLoginRequest.value=values

         }
     }
}