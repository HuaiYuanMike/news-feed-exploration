package com.example.newsFeed.noteFeed.presentation.noteFeed

import com.example.newsFeed.noteFeed.model.Note

sealed class Change(val isLoading: Boolean) {
    object Loading : Change(isLoading = true)
    data class AllNotesRetrieved(val notes: List<Note>): Change(isLoading = false)
    data class NoteInserted(val notes: List<Note>): Change(isLoading = false)
    data class NoteDeleted(val notes: List<Note>): Change(isLoading = false)
    data class Error(val error: Throwable): Change(isLoading = false)
}