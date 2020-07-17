package com.example.newsFeed.mainFeed.domain

import com.example.newsFeed.mainFeed.data.Note

class NoteUsecase {
    // Retrieve all notes
    // TODO What would be the return type?
    // 1. suspend function to return a List<Note> data type
    // 2. a LiveDate<List<Note>>
    // TODO What would be the interaction between usecase and repository
    suspend fun retrieveNotes(): List<Note> {
        return emptyList()
    }
    // Insert one Note

    // Delete one Note

    // Update one Note
}