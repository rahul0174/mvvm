package com.example.ui

import android.app.Application
import com.example.api.QuoteDataBase
import com.example.api.RetrofitHelper
import com.example.api.RetrofitService
import com.example.repository.QuoteRepository


class MainApplication:Application() {
    lateinit var repository: QuoteRepository
    lateinit var quoteDataBase: QuoteDataBase
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(RetrofitService::class.java)
               repository= QuoteRepository(quoteService,quoteDataBase)
    }

}