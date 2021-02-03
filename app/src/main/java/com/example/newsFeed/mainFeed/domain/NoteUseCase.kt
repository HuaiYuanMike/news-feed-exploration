package com.example.newsFeed.mainFeed.domain

import android.util.Log
import com.example.newsFeed.mainFeed.model.Note
import com.example.newsFeed.mainFeed.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val repository: NoteRepository) {

    fun retrieveAllNotes(): Flow<List<Note>> {
        return repository.retrieveAllNotes()
    }

    // Insert one Note
    suspend fun insertNote(note: Note) {
        var newNote = note.copy()
        if (newNote.imageUri.isEmpty()) {
            val imageUrl = repository.getRandomCatImage().imageUrl
            newNote = note.copy(imageUri = imageUrl)
            Log.d(this.javaClass.simpleName, "New note image url: ${newNote.imageUri}")
        }
        Log.d(this.javaClass.simpleName, "Attempt to insert note $note")
        repository.insertNode(newNote)
    }

    // Delete one Note
    suspend fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

    // Update one Note
}