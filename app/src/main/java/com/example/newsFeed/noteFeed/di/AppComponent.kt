package com.example.newsFeed.noteFeed.di

import com.example.newsFeed.noteFeed.presentation.noteEdit.EditNoteViewModel
import com.example.newsFeed.noteFeed.presentation.noteFeed.NoteFeedViewModelFactory
import com.example.newsFeed.noteFeed.workManager.DailyNoteInsertWorker
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(noteFeedViewModelFactory: NoteFeedViewModelFactory)
    fun inject(editNoteViewModel: EditNoteViewModel)
    fun inject(dailyNoteInsertWorker: DailyNoteInsertWorker)
}