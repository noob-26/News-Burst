package com.souraprabha.newsburst.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNewsInterface {
    @GET("top-headlines")
    fun getHeadlines(@Query("country") country: String, @Query("apiKey") key: String): Call<NewsItem>

    @GET("top-headlines")
    fun getNews(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") key: String): Call<NewsItem>

    @GET("everything")
    fun getAgencyNews(@Query("domains") link: String, @Query("apiKey") key: String): Call<NewsItem>
}