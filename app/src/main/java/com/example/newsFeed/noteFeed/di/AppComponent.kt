package com.example.newsFeed.noteFeed.di

import com.example.newsFeed.noteFeed.presentation.noteEdit.EditNoteViewModel
import com.example.newsFeed.noteFeed.presentation.noteFeed.MainFeedViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainFeedViewModelFactory: MainFeedViewModelFactory)
    fun inject(editNoteViewModel: EditNoteViewModel)
}