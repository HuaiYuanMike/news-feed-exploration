package com.example.newsFeed.di

import com.example.newsFeed.mainFeed.presentation.MainFeedViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainFeedViewModelFactory: MainFeedViewModelFactory)
}