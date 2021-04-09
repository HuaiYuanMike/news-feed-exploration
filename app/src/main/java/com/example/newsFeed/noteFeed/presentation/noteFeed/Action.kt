package com.example.newsFeed.noteFeed.presentation.noteFeed

import com.example.newsFeed.noteFeed.model.Note

sealed class Action {
    object GetAllNotes : Action()
    data class InsertNote(val note: Note) : Action()
    data class DeleteNote(val note: Note) : Action()
}