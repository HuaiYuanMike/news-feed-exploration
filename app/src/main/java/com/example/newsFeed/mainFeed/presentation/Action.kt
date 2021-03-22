package com.example.newsFeed.mainFeed.presentation

import com.example.newsFeed.mainFeed.model.Note

sealed class Action {
    object GetAllNotes : Action()
    data class InsertNote(val note: Note) : Action()
    data class DeleteNote(val note: Note) : Action()
}