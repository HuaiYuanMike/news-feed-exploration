package com.example.newsFeed.noteFeed.usecase

import android.util.Log
import com.example.newsFeed.noteFeed.model.Note
import com.example.newsFeed.noteFeed.presentation.noteFeed.Change
import com.example.newsFeed.noteFeed.repository.NoteFeedRepository
import javax.inject.Inject

class NoteFeedUseCase @Inject constructor(private val repository: NoteFeedRepository) {

    //For the purpose of MVI, a suspend function returning a list of notes is probably better than
    // a Flow of list of notes which receives the updates to the notes automatically.
    suspend fun retrieveAllNotes(): Change.AllNotesRetrieved
        = Change.AllNotesRetrieved(repository.retrieveAllNotes())

    suspend fun insertNote(note: Note): Change {
        Log.d("mikelog", "InsertNote performed in ${Thread.currentThread().name} thread.")
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