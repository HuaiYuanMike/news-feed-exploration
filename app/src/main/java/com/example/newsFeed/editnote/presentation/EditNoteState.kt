package com.example.newsFeed.editnote.presentation

sealed class EditNoteState {
    object Idle : EditNoteState()
    object Inserting : EditNoteState()
    object Finished : EditNoteState()
}