package com.example.newsFeed.mainFeed.domain

import android.util.Log
import com.example.newsFeed.mainFeed.model.Note
import com.example.newsFeed.mainFeed.presentation.MainFeedViewModel
import com.example.newsFeed.mainFeed.repository.NoteRepository
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val repository: NoteRepository) {

    //For the purpose of MVI, a suspend function returning a list of notes is probably better than
    // a Flow of list of notes which receives the updates to the notes automatically.
    suspend fun retrieveAllNotes(): MainFeedViewModel.Change.AllNotesRetrieved
        = MainFeedViewModel.Change.AllNotesRetrieved(repository.retrieveAllNotes())

    suspend fun insertNote(note: Note): MainFeedViewModel.Change {
        var newNote = note.copy()
        if (newNote.imageUri.isEmpty()) {
            val imageUrl = repository.getRandomCatImage().imageUrl
            newNote = note.copy(imageUri = imageUrl)
            Log.d(this.javaClass.simpleName, "New note image url: ${newNote.imageUri}")
        }
        Log.d(this.javaClass.simpleName, "Attempt to insert note $note")
        repository.insertNode(newNote)

        return MainFeedViewModel.Change.NoteInserted(repository.retrieveAllNotes())
    }

    suspend fun deleteNote(note: Note): MainFeedViewModel.Change.NoteDeleted {
        repository.deleteNote(note)
        return MainFeedViewModel.Change.NoteDeleted(repository.retrieveAllNotes())
    }

    // TODO Update one Note
}