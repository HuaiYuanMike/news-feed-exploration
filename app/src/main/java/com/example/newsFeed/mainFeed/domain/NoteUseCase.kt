package com.example.newsFeed.mainFeed.domain

import com.example.newsFeed.NewsFeedApplication
import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.repository.NoteRepository
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val repository: NoteRepository) {
    // Retrieve all notes
    // TODO What would be the return type?
    // 1. suspend function to return a List<Note> data type
    // 2. a LiveDate<List<Note>>
    // TODO What would be the interaction between usecase and repository

    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    suspend fun retrieveAllNotes(): List<Note> {
        return repository.retrieveAllNotes()
    }
    // Insert one Note
    suspend fun insertNote(note: Note) {
        repository.insertNode(note)
    }

    // Delete one Note

    // Update one Note
}