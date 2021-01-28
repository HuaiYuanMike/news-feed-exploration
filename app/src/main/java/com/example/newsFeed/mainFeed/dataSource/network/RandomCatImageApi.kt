package com.example.newsFeed.mainFeed.dataSource.network

import com.example.newsFeed.mainFeed.model.CatImage
import retrofit2.http.GET

interface RandomCatImageApi {

    @GET("meow")
    suspend fun getRandomCatImage(): CatImage
}