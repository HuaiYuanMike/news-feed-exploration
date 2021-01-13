package com.example.newsFeed.editnote.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsFeed.NewsFeedApplication
import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.domain.NoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel(val state: SavedStateHandle) : ViewModel() {

    @Inject
    lateinit var noteUseCase: NoteUseCase

    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteUseCase.insertNote(note)
        }
    }
}