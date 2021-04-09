package com.example.newsFeed.noteFeed.dataSource.network

import com.example.newsFeed.noteFeed.model.CatImage
import retrofit2.http.GET

interface RandomCatImageService {

    @GET("meow")
    suspend fun getRandomCatImage(): CatImage
}