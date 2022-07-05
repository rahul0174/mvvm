package com.example.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.model.Cities


@Dao
interface QuoteDao {
@Insert
    suspend fun addQuote(quoteDao: List<Cities>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes():List<Cities>

}