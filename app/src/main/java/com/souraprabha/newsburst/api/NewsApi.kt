package com.souraprabha.newsburst.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NewsApi{
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RetrofitNewsInterface by lazy {
        retrofit.create(RetrofitNewsInterface::class.java)
    }
}