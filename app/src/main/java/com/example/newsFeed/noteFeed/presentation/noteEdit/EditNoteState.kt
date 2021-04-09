package com.example.newsFeed.noteFeed.presentation.noteEdit

sealed class EditNoteState {
    object Idle : EditNoteState()
    object Inserting : EditNoteState()
    object Finished : EditNoteState()
}