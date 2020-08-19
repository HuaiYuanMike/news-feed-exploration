package com.example.newsFeed.mainFeed.domain

import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.repository.NoteRepository
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val repository: NoteRepository) {

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