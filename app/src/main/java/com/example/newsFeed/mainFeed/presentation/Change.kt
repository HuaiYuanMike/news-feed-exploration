package com.example.newsFeed.mainFeed.presentation

import com.example.newsFeed.mainFeed.model.Note

sealed class Change(val isLoading: Boolean) {
    object Loading : Change(isLoading = true)
    data class AllNotesRetrieved(val notes: List<Note>): Change(isLoading = false)
    data class NoteInserted(val notes: List<Note>): Change(isLoading = false)
    data class NoteDeleted(val notes: List<Note>): Change(isLoading = false)
    data class Error(val error: Throwable): Change(isLoading = false)
}