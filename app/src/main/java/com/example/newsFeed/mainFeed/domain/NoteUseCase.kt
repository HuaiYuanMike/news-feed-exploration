package com.example.newsFeed.mainFeed.domain

import android.util.Log
import com.example.newsFeed.mainFeed.model.Note
import com.example.newsFeed.mainFeed.presentation.Change
import com.example.newsFeed.mainFeed.repository.NoteRepository
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val repository: NoteRepository) {

    //For the purpose of MVI, a suspend function returning a list of notes is probably better than
    // a Flow of list of notes which receives the updates to the notes automatically.
    suspend fun retrieveAllNotes(): Change.AllNotesRetrieved
        = Change.AllNotesRetrieved(repository.retrieveAllNotes())

    suspend fun insertNote(note: Note): Change {
        var newNote = note.copy()
        if (newNote.imageUri.isEmpty()) {
            val imageUrl = repository.getRandomCatImage().imageUrl
            newNote = note.copy(imageUri = imageUrl)
            Log.d(this.javaClass.simpleName, "New note image url: ${newNote.imageUri}")
        }
        Log.d(this.javaClass.simpleName, "Attempt to insert note $note")
        repository.insertNode(newNote)

        return Change.NoteInserted(repository.retrieveAllNotes())
    }

    suspend fun deleteNote(note: Note): Change.NoteDeleted {
        repository.deleteNote(note)
        return Change.NoteDeleted(repository.retrieveAllNotes())
    }

    // TODO Update one Note
}