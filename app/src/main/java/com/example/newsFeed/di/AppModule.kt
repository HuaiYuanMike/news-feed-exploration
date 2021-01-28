package com.example.newsFeed.di

import android.content.Context
import androidx.room.Room
import com.example.newsFeed.mainFeed.dataSource.network.RetrofitHelper
import com.example.newsFeed.mainFeed.dataSource.persistent.AppDatabase
import com.example.newsFeed.mainFeed.dataSource.persistent.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideApplicationContext() = context

    @Provides
    @Singleton
    fun provideAppDatabase() =
        Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRandomCatImageRetrofit() = RetrofitHelper.randomCatImageApi
}