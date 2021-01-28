package com.example.newsFeed.mainFeed.dataSource.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val randomCatImageApi: RandomCatImageApi = getRetrofit("https://aws.random.cat/").create(RandomCatImageApi::class.java)

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}