package com.example.newsFeed.noteFeed.presentation.noteEdit

import androidx.lifecycle.*
import com.example.newsFeed.NewsFeedApplication
import com.example.newsFeed.noteFeed.model.Note
import com.example.newsFeed.noteFeed.usecase.NoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel(val state: SavedStateHandle) : ViewModel() {

    @Inject
    lateinit var noteUseCase: NoteUseCase

    private val innerEditNoteState: MutableLiveData<EditNoteState> = MutableLiveData(
        EditNoteState.Idle)

    val editNoteStates: LiveData<EditNoteState>
        get() = innerEditNoteState


    init {
        NewsFeedApplication.instance.appComponent.inject(this)
    }

    fun insertNote(note: Note) {
        innerEditNoteState.postValue(EditNoteState.Inserting)
        viewModelScope.launch(Dispatchers.Default) {
            noteUseCase.insertNote(note)
            innerEditNoteState.postValue(EditNoteState.Finished)
        }
    }
}