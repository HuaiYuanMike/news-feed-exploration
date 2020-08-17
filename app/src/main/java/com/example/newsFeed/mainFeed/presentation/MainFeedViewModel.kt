package com.example.newsFeed.mainFeed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsFeed.mainFeed.data.Note
import com.example.newsFeed.mainFeed.domain.NoteUseCase
import kotlinx.coroutines.*

class MainFeedViewModel constructor(private val noteUseCase: NoteUseCase) : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = _notes

    // This does not need to be suspend
    fun getAllNotes() = GlobalScope.launch(Dispatchers.Default) {
        _notes.postValue(noteUseCase.retrieveAllNotes())
    }
}
// TODO List to confirm
// 1. Kotlin coroutine launchers - launch(), async() - and withContext()
//      Kotlin running multiple coroutines concurrently
// 1.1 Kotlin CoroutineScope - ViewModel scope
// 2. ViewModel kotlin ktx byViewModels() method
// 3. Dependency injection or something else which remove the usage of ViewModelProvider and Factory
// 4. ViewModel SavedStateHandle
// 5. HILT
// 6. Retrofit - REST API - HTTP ?
// 7. Image loading library