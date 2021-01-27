package com.example.newsFeed.mainFeed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsFeed.mainFeed.model.Note
import com.example.newsFeed.mainFeed.domain.NoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFeedViewModel @Inject constructor(private val noteUseCase: NoteUseCase) : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = _notes

    // This does not need to be suspend
    fun getAllNotes() = viewModelScope.launch(Dispatchers.Default) {
        noteUseCase.retrieveAllNotes().collect {
            list -> _notes.postValue(list)
        }
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.Default) {
        noteUseCase.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteUseCase.deleteNote(note)
    }
}

// TODO Next Big features
// 1. Action buttons to insert
// 1.2 Dialog for adding new note
// 2. DiffUtil for recyclerView
// 3. Swipe to remove
// 4. Drag to change position
// 5. MVI
// 6. Bitrise Test
// 67. Bitrise Code coverage

// TODO List to confirm
// 1. Kotlin coroutine launchers - launch(), async() - and withContext()
//      Kotlin running multiple coroutines concurrently
// 1.1 Kotlin CoroutineScope - ViewModel scope (V)
// 1.2 Kotlin coroutineScope suspend function - coordinate the child  (v)
// 1.3 Kotlin Coroutine Channels
// 2. ViewModel Android fragment ktx byViewModels() method (v)
// 3. Dependency injection or something else which remove the usage of ViewModelProvider and Factory
// 4. ViewModel SavedStateHandle
// 5. Paging
// 5. HILT
// 6. Retrofit - REST API - HTTP ?
// 7. Image loading library


// TODO Future
//  - Paging Library from Jetpack
// https://proandroiddev.com/exploring-paging-library-from-jetpack-c661c7399662