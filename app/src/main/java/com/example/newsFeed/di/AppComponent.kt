package com.example.newsFeed.di

import com.example.newsFeed.mainFeed.domain.NoteUseCase
import com.example.newsFeed.mainFeed.repository.NoteRepository
import dagger.Component

@Component (modules = [AppModule::class])
interface AppComponent {
    fun inject(noteRepository: NoteRepository)

    fun inject(noteUseCase: NoteUseCase)
}