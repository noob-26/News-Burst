package com.souraprabha.newsburst.api

import com.google.gson.annotations.SerializedName

data class Articles(
    val author: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val time: String?
)