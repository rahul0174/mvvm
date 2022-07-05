package com.example.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.model.Cities

@Database(entities = [Cities::class], version = 1)
abstract class QuoteDataBase:RoomDatabase() {

    abstract fun quoteDao():QuoteDao

    companion object{
        @Volatile
        private var INSTANCE: QuoteDataBase?=null
        fun getDatabase(context: Context):QuoteDataBase{
            if (INSTANCE==null){
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,QuoteDataBase::class.java,"quoteDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}