package com.example.newsFeed

import android.app.Application
import com.example.newsFeed.di.AppComponent
import com.example.newsFeed.di.AppModule
import com.example.newsFeed.di.DaggerAppComponent

class NewsFeedApplication : Application() {

    companion object {
        lateinit var instance: NewsFeedApplication
    }


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        instance = this
    }
}